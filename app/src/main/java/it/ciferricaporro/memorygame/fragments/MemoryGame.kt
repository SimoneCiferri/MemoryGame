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
    private val images = mutableListOf(R.drawable.pikachu,
            R.drawable.bulbasaur, R.drawable.charmander, R.drawable.gengar,
            R.drawable.squirtle, R.drawable.mew)
    private var indexOfSelectedCard: Int? = null
    private var milliS: Long = 0
    private var milliStop: Long = 0
    private var timeInMill: Long = 0
    private lateinit var zoom: Animator
    private lateinit var btnSaveScore: Button
    private lateinit var tvErr: TextView
    var ost = mp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoryGameBinding.inflate(layoutInflater, container, false)
        setUiController()
        return binding.root
    }

    private fun setUiController(){
        bindingM.bottomNavigationView.isVisible = true
        images.addAll(images)

        btnSaveScore = binding.btnSaveScore
        btnSaveScore.isVisible = false
        tvErr = binding.tvErr

        buttons = listOf(binding.imageButton2, binding.imageButton3, binding.imageButton4,
                binding.imageButton5, binding.imageButton6, binding.imageButton7,
                binding.imageButton8, binding.imageButton9, binding.imageButton10,
                binding.imageButton11, binding.imageButton13, binding.imageButton14
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
                }
            }
        }

        val btnStart = binding.btnNewGame
        zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(btnStart)
        zoom.duration = 1000
        zoom.start()
        btnStart.setOnClickListener {
            newGame()
        }

        btnSaveScore.setOnClickListener {
            val navToSave = MemoryGameDirections.actionMemoryGameToSaveScoreFragment(tvErr.text.toString().toInt(), getTime(), timeInMill)
            Navigation.findNavController(binding.root).navigate(navToSave)
        }

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
        if(indexOfSelectedCard==null){
            restoreCards()
            indexOfSelectedCard = position
        }else{
            checkForMatch(indexOfSelectedCard!!, position, tvErr)
            indexOfSelectedCard = null
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

    private fun checkForMatch(indexOfSelectedCard: Int, position: Int, tvErr: TextView) {
        if(cards[indexOfSelectedCard].ID == cards[position].ID){
            cards[indexOfSelectedCard].isMatched = true
            cards[position].isMatched = true
            flipCard(buttons[position])
            flipCard(buttons[indexOfSelectedCard])
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
        for(btn in buttons){
            btn.isEnabled = true
        }
        for(btn in buttons){
            flipCard(btn)
        }
        tvErr.text = "0"
        btnSaveScore.isVisible = false
        indexOfSelectedCard = null
        updateViews()
        milliS = System.currentTimeMillis()
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