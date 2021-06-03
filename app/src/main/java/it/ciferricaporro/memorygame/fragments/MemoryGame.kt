package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import it.ciferricaporro.memorygame.MainActivity.Companion.binding
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.model.Card
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.delay


class MemoryGame : Fragment() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<Card>
    private var indexOfSelectedCarrd: Int? = null
    private val images = mutableListOf(R.drawable.ic_baseline_local_taxi, R.drawable.ic_baseline_mood, R.drawable.ic_baseline_phone_android, R.drawable.ic_baseline_sick, R.drawable.ic_baseline_thumb_up)
    private lateinit var btnSaveScore: Button
    private lateinit var tvErr: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewMG = inflater.inflate(R.layout.fragment_memory_game, container, false)
        binding.bottomNavigationView.isVisible = true
        btnSaveScore = viewMG.findViewById<Button>(R.id.btnSaveScore)
        tvErr = viewMG.findViewById<TextView>(R.id.tvErr)
        setUiController(viewMG)
        return viewMG
    }

    private fun setUiController(viewMG: View){
        btnSaveScore.isVisible = false
        tvErr.text = "0"
        images.addAll(images)
        images.shuffle()

        buttons = listOf(
            viewMG.findViewById(R.id.imageButton2),
            viewMG.findViewById(R.id.imageButton3),
            viewMG.findViewById(R.id.imageButton4),
            viewMG.findViewById(R.id.imageButton5),
            viewMG.findViewById(R.id.imageButton6),
            viewMG.findViewById(R.id.imageButton7),
            viewMG.findViewById(R.id.imageButton8),
            viewMG.findViewById(R.id.imageButton9),
            viewMG.findViewById(R.id.imageButton10),
            viewMG.findViewById(R.id.imageButton11)
        )

        cards = buttons.indices.map { index ->
            Card(images[index])
        }

        buttons.forEachIndexed { index, btn ->
            btn.setOnClickListener{
                updateModels(index)
                updateViews()
                if(checkAll()){
                    btnSaveScore.isVisible = true
                    startTimer(viewMG, false)
                    //Toast.makeText(this, "You Win!!", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewMG.findViewById<Button>(R.id.btnNewGame).setOnClickListener {
            btnSaveScore.isVisible = false
            tvErr.text = "0"
            newGame()
            startTimer(viewMG, true)
        }

        viewMG.findViewById<Button>(R.id.btnSaveScore).setOnClickListener {
            val navToSave = MemoryGameDirections.actionMemoryGameToSaveScoreFragment(tvErr.text.toString().toInt())
            Navigation.findNavController(viewMG).navigate(navToSave)
        }

        // access the chronometer from XML file
        //val meter = viewMG.findViewById<Chronometer>(R.id.c_meter)

        //access the button using id
        /*val btn = viewMG.findViewById<Button>(R.id.btn)
        btn?.setOnClickListener(object : View.OnClickListener {

            var isWorking = false

            override fun onClick(v: View) {
                meter.setBase(SystemClock.elapsedRealtime())
                if (!isWorking) {
                    meter.start()
                    isWorking = true
                } else {
                    meter.stop()
                    isWorking = false
                }

                btn.setText(if (isWorking) R.string.start else R.string.stop)

                Toast.makeText(requireContext(), getString(
                    if (isWorking)
                        R.string.working
                    else
                        R.string.stopped),
                    Toast.LENGTH_SHORT).show()

                if(!isWorking){
                    val time=SystemClock.elapsedRealtime() -meter.base
                    Toast.makeText(requireContext(), time.toString() ,Toast.LENGTH_LONG).show()
                }
            }
        })
         */
        startTimer(viewMG, true)
    }

    private fun updateViews(){
        cards.forEachIndexed{ index, card ->
            val btn = buttons[index]
            if(card.isMatched){
                btn.alpha = 0.1f
            }
            else{
                btn.alpha = 1f
            }
            btn.setImageResource(if(card.isFaceUp) card.ID else R.drawable.ic_baseline_help_outline_24)
        }
    }

    private fun updateModels(position:Int){
        val card = cards[position]

        if (card.isFaceUp){
            //Toast.makeText(this, "invalid move!",Toast.LENGTH_SHORT).show()
            return
        }

        if(indexOfSelectedCarrd==null){
            restoreCards()
            indexOfSelectedCarrd = position
        }else{
            checkForMatch(indexOfSelectedCarrd!!, position, tvErr)
            indexOfSelectedCarrd = null

        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(indexOfSelectedCarrd: Int, position: Int, tvErr: TextView) {
        if(cards[indexOfSelectedCarrd].ID == cards[position].ID){
            //Toast.makeText(this, "Match Found!!",Toast.LENGTH_LONG).show()
            cards[indexOfSelectedCarrd].isMatched = true
            cards[position].isMatched = true
        }else{
            tvErr.text = (tvErr.text.toString().toInt() + 1).toString()
        }
    }

    private fun checkAll(): Boolean {
        for(card in cards){
            if(!card.isMatched){
                return false
            }
        }
        //Toast.makeText(this, "All Matched!!",Toast.LENGTH_SHORT).show()
        return true
    }

    private fun newGame(){
        images.shuffle()
        cards = buttons.indices.map { index ->
            Card(images[index])
        }
        for(card in cards){
            card.isMatched = false
            card.isFaceUp = false
        }
        indexOfSelectedCarrd = null
        updateViews()
        btnSaveScore.isVisible = true
    }

    private fun startTimer(viewMG: View, state: Boolean){
        val meter = viewMG.findViewById<Chronometer>(R.id.c_meter)
        meter.setBase(SystemClock.elapsedRealtime())
        if(state){
            meter.start()
        }else{
            meter.stop()
            val time = SystemClock.elapsedRealtime() - meter.getBase()
            Toast.makeText(requireContext(), time.toString() ,Toast.LENGTH_SHORT).show()
        }

    }

}