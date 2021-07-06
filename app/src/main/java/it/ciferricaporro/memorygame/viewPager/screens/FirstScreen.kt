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
import androidx.core.animation.doOnRepeat
import androidx.viewpager2.widget.ViewPager2
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val viewF = inflater.inflate(R.layout.fragment_first_screen, container, false)
        binding = FragmentFirstScreenBinding.inflate(layoutInflater, container, false)
        //val fing = binding.ivFinger
        //val fing = viewF.findViewById<ImageView>(R.id.ivFinger)
        /*val zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(fing)
        zoom.duration = 1000
        zoom.doOnEnd {
            zoom.start()
        }
        zoom.start()*/

        val viewP = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.tvNext.setOnClickListener {

            viewP?.currentItem=1

        }
        /*
        viewF.findViewById<TextView>(R.id.tvNext).setOnClickListener {

            viewP?.currentItem=1

        }
        */
        return binding.root
        //return viewF
    }


}