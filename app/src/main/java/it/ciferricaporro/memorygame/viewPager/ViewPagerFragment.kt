package it.ciferricaporro.memorygame.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import it.ciferricaporro.memorygame.R

class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewPF = inflater.inflate(R.layout.fragment_view_pager, container, false)
        /*
        val fragmenList = arrayListOf<Fragment>( FirstScreen(), SecondScreen(), ThirdScreen() )

        val adapter = ViewPagerAdapter(fragmenList, requireActivity().supportFragmentManager, lifecycle)

        viewPF.findViewById<ViewPager2>(R.id.viewPager).adapter = adapter
        */
        return viewPF
    }
}