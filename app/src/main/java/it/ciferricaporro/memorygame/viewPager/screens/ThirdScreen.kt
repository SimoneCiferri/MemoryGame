package it.ciferricaporro.memorygame.viewPager.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
        binding = FragmentThirdScreenBinding.inflate(layoutInflater, container, false)
        setUiController()
        return binding.root
    }

    private fun setUiController(){
        binding.tvFinish.setOnClickListener {
            val navToGame = ViewPagerFragmentDirections.actionViewPagerFragmentToMemoryGame()
            Navigation.findNavController(binding.root).navigate(navToGame)
            onTutFinished()
        }
    }

    private fun onTutFinished(){
        val sharedPref = requireActivity().getSharedPreferences(tutorial, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(finish, true)
        editor.apply()
    }
}