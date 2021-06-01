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

class ThirdScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewT = inflater.inflate(R.layout.fragment_third_screen, container, false)
/*
        viewT.findViewById<TextView>(R.id.tvFinish).setOnClickListener {
            val navToHome = ViewPagerFragmentDirections.actionViewPagerFragmentToFirstFragment()
            Navigation.findNavController(viewT).navigate(navToHome)
            onTutFinished()
        }
 */
        return viewT
    }

    private fun onTutFinished(){
        val sharedPref = requireActivity().getSharedPreferences("Tutorial", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finish", true)
        editor.apply()
    }
}