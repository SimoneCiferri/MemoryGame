package it.ciferricaporro.memorygame.viewPager.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentThirdScreenBinding
import it.ciferricaporro.memorygame.viewPager.ViewPagerFragmentDirections

class ThirdScreen : Fragment() {

    private val tutorial = "Tutorial"
    private val finish = "Finish"
    private lateinit var binding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val viewT = inflater.inflate(R.layout.fragment_third_screen, container, false)
        binding = FragmentThirdScreenBinding.inflate(layoutInflater, container, false)

        binding.tvFinish.setOnClickListener {
            val navToGame = ViewPagerFragmentDirections.actionViewPagerFragmentToMemoryGame()
            Navigation.findNavController(binding.root).navigate(navToGame)
            onTutFinished()
        }
        /*
        viewT.findViewById<TextView>(R.id.tvFinish).setOnClickListener {
            val navToGame = ViewPagerFragmentDirections.actionViewPagerFragmentToMemoryGame()
            Navigation.findNavController(viewT).navigate(navToGame)
            onTutFinished()
        }
        */
        return binding.root
        //return viewT
    }

    private fun onTutFinished(){
        val sharedPref = requireActivity().getSharedPreferences(tutorial, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(finish, true)
        editor.apply()
    }
}