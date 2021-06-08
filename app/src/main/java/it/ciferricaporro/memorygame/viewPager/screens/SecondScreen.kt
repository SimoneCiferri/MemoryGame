package it.ciferricaporro.memorygame.viewPager.screens

import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.viewpager2.widget.ViewPager2
import it.ciferricaporro.memorygame.R

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewS = inflater.inflate(R.layout.fragment_second_screen, container, false)
        val viewP = activity?.findViewById<ViewPager2>(R.id.viewPager)

        viewS.findViewById<TextView>(R.id.tvNext2).setOnClickListener {

            viewP?.currentItem=2

        }
        return viewS
    }

}