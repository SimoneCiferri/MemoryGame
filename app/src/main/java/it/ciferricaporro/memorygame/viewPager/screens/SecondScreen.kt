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
import it.ciferricaporro.memorygame.databinding.FragmentFirstScreenBinding
import it.ciferricaporro.memorygame.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    private lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val viewS = inflater.inflate(R.layout.fragment_second_screen, container, false)
        binding = FragmentSecondScreenBinding.inflate(layoutInflater, container, false)

        val viewP = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.tvNext2.setOnClickListener {

            viewP?.currentItem=2

        }
        /*
        viewS.findViewById<TextView>(R.id.tvNext2).setOnClickListener {

            viewP?.currentItem=2

        }
        */
        return binding.root
        //return viewS
    }

}