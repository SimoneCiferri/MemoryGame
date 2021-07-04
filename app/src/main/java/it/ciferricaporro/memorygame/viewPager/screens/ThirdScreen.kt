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
import it.ciferricaporro.memorygame.viewPager.ViewPagerFragmentDirections

class ThirdScreen : Fragment() {

    private val tutorial = "Tutorial"
    private val finish = "Finish"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewT = inflater.inflate(R.layout.fragment_third_screen, container, false)

        viewT.findViewById<TextView>(R.id.tvFinish).setOnClickListener {
            val navToGame = ViewPagerFragmentDirections.actionViewPagerFragmentToMemoryGame()
            Navigation.findNavController(viewT).navigate(navToGame)
            onTutFinished()
        }

        return viewT
    }

    private fun onTutFinished(){
        val sharedPref = requireActivity().getSharedPreferences(tutorial, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(finish, true)
        editor.apply()
    }
}