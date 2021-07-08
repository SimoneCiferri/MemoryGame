package it.ciferricaporro.memorygame.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.ciferricaporro.memorygame.databinding.FragmentViewPagerBinding
import it.ciferricaporro.memorygame.viewPager.screens.FirstScreen
import it.ciferricaporro.memorygame.viewPager.screens.SecondScreen
import it.ciferricaporro.memorygame.viewPager.screens.ThirdScreen

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        setAdapter()
        return binding.root
    }

    private fun setAdapter(){
        val fragmentList = arrayListOf( FirstScreen(), SecondScreen(), ThirdScreen() )
        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
    }
}