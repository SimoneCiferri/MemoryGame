package it.ciferricaporro.memorygame.fragments

import android.animation.AnimatorInflater
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import it.ciferricaporro.memorygame.R


class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewA = inflater.inflate(R.layout.fragment_about, container, false)
        val arrow = viewA.findViewById<ImageView>(R.id.ivArrow)
        val zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(arrow)
        zoom.duration = 800
        zoom.doOnEnd {
            zoom.start()
        }
        zoom.start()
        val tvLink = viewA.findViewById<TextView>(R.id.tvGitLink)
        tvLink.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(tvLink.text.toString())
            startActivity(i)
        }

        return viewA
    }

}