package it.ciferricaporro.memorygame.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import it.ciferricaporro.memorygame.MainActivity.Companion.bindingM
import it.ciferricaporro.memorygame.MainActivity.Companion.mp
import it.ciferricaporro.memorygame.MainActivity.Companion.mpState
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentMemoryGameBinding
import it.ciferricaporro.memorygame.model.Card
import kotlinx.android.synthetic.main.fragment_memory_game.*


class MemoryGame : Fragment() {

    private lateinit var binding: FragmentMemoryGameBinding
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<Card>
    private val images = mutableListOf(R.drawable.pikachu, R.drawable.bulbasaur, R.drawable.charmander, R.drawable.gengar, R.drawable.squirtle, R.drawable.mew)
    private var indexOfSelectedCarrd: Int? = null
    private var milliS: Long = 0
    private var milliStop: Long = 0
    private var timeInMill: Long = 0
    private lateinit var zoom: Animator
    private lateinit var btnSaveScore: Button
    private lateinit var tvErr: TextView
    //companion object OST giulio
    var ost = mp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val viewMG = inflater.inflate(R.layout.fragment_memory_game, container, false)
        binding = FragmentMemoryGameBinding.inflate(layoutInflater, container, false)
        setUiController()
        return binding.root
        //return viewMG
    }

    private fun setUiController(){
        bindingM.bottomNavigationView.isVisible = true
        btnSaveScore = binding.btnSaveScore
        btnSaveScore.isVisible = false
        //tvErr = viewMG.findViewById<TextView>(R.id.tvErr)
        tvErr = binding.tvErr
        images.addAll(images)
        /*
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
                viewMG.findViewById(R.id.imageButton11),
                viewMG.findViewById(R.id.imageButton13),
                viewMG.findViewById(R.id.imageButton14)
        )
        */
        buttons = listOf(
                binding.imageButton2,
                binding.imageButton3,
                binding.imageButton4,
                binding.imageButton5,
                binding.imageButton6,
                binding.imageButton7,
                binding.imageButton8,
                binding.imageButton9,
                binding.imageButton10,
                binding.imageButton11,
                binding.imageButton13,
                binding.imageButton14
        )

        for(btn in buttons){
            btn.isEnabled = false
            btn.alpha = 0.5f
        }

        buttons.forEachIndexed { index, btn ->
            btn.setOnClickListener{
                updateModels(index)
                updateViews()
                if(checkAll()){
                    milliStop = System.currentTimeMillis()
                    zoom.setTarget(btnSaveScore)
                    zoom.start()
                    btnSaveScore.isVisible = true
                    //Toast.makeText(this, "You Win!!", Toast.LENGTH_LONG).show()
                }
            }
        }

        //val btnStart = viewMG.findViewById<Button>(R.id.btnNewGame)
        val btnStart = binding.btnNewGame
        zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(btnStart)
        zoom.duration = 1000
        zoom.start()
        btnStart.setOnClickListener {
            newGame()
        }
        /*
        viewMG.findViewById<Button>(R.id.btnSaveScore).setOnClickListener {
            val navToSave = MemoryGameDirections.actionMemoryGameToSaveScoreFragment(tvErr.text.toString().toInt(), getTime(), timeInMill)
            Navigation.findNavController(viewMG).navigate(navToSave)
        }
        */
        btnSaveScore.setOnClickListener {
            val navToSave = MemoryGameDirections.actionMemoryGameToSaveScoreFragment(tvErr.text.toString().toInt(), getTime(), timeInMill)
            Navigation.findNavController(binding.root).navigate(navToSave)
        }


        //val ibVolume = viewMG.findViewById<ImageView>(R.id.imageAudio)
        val ibVolume = binding.imageAudio
        volumeIcSet(ibVolume)
        ibVolume.setOnClickListener{
            if (ost.isPlaying){
                imageAudio.setImageResource(R.drawable.volume_off)
                ost.pause()
                mpState = false
            }
            else{
                imageAudio.setImageResource(R.drawable.volume_up)
                ost.start()
                mpState = true
            }
        }

    }

    private fun updateViews(){
        cards.forEachIndexed{ index, card ->
            val btn = buttons[index]
            if(card.isMatched){
                btn.alpha = 0.3f
            }
            else{
                btn.alpha = 1f
            }
            btn.setImageResource(if(card.isFaceUp) card.ID else R.drawable.quesmark)
        }
    }

    private fun updateModels(position:Int){
        val card = cards[position]

        if (card.isFaceUp){
            Toast.makeText(requireContext(), "invalid move!",Toast.LENGTH_SHORT).show()
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
            flipCard(buttons[position])
            flipCard(buttons[indexOfSelectedCarrd])
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
        //images.shuffle()
        cards = buttons.indices.map { index ->
            Card(images[index])
        }
        for(card in cards){
            card.isMatched = false
            card.isFaceUp = false
        }
        for(btn in buttons){
            btn.isEnabled = true
        }
        for(btn in buttons){
            flipCard(btn)
        }
        tvErr.text = "0"
        btnSaveScore.isVisible = false
        indexOfSelectedCarrd = null
        updateViews()
        milliS = System.currentTimeMillis()
        //btnSaveScore.isVisible = true
    }

    private fun getTime(): String{
        timeInMill = (milliStop - milliS)
        var min = ((timeInMill/1000)/60).toString()
        if(min == "0"){
            min = "00"
        }
        var sec = ((timeInMill/1000)%60).toString()
        if(sec.toInt() < 10){
            sec = "0" + sec
        }
        val msec = (((timeInMill/10)/10)%10).toString() +((timeInMill/10)%10).toString() + (timeInMill%10).toString()
        return min + ":" + sec + ":" + msec
    }

    private fun flipCard(btn: ImageButton){
        val flip = AnimatorInflater.loadAnimator(requireContext(), R.animator.flip)
        flip.duration = 350
        flip.setTarget(btn)
        flip.start()
    }

    private fun volumeIcSet(ibVolume: ImageView) {
        if (ost.isPlaying){
            ibVolume.setImageResource(R.drawable.volume_up)
            mpState = true
        }
        else{
            ibVolume.setImageResource(R.drawable.volume_off)
            mpState = false
        }
    }
}