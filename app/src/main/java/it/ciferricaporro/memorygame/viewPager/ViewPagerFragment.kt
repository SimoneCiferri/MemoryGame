package it.ciferricaporro.memorygame.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentViewPagerBinding
import it.ciferricaporro.memorygame.viewPager.screens.FirstScreen
import it.ciferricaporro.memorygame.viewPager.screens.SecondScreen
import it.ciferricaporro.memorygame.viewPager.screens.ThirdScreen

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //val viewPF = inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        val fragmentList = arrayListOf<Fragment>( FirstScreen(), SecondScreen(), ThirdScreen() )

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        //viewPF.findViewById<ViewPager2>(R.id.viewPager).adapter = adapter
        binding.viewPager.adapter = adapter

        return binding.root
        //return viewPF
    }
}