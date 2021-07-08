package it.ciferricaporro.memorygame.viewPager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstScreenBinding.inflate(layoutInflater, container, false)
        setUiController()
        return binding.root
    }

    private fun setUiController(){
        val viewP = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.tvNext.setOnClickListener {
            viewP?.currentItem=1
        }
    }
}