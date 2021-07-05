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
import it.ciferricaporro.memorygame.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val viewA = inflater.inflate(R.layout.fragment_about, container, false)
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        //val arrow = viewA.findViewById<ImageView>(R.id.ivArrow)
        val arrow = binding.ivArrow
        val zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(arrow)
        zoom.duration = 800
        zoom.doOnEnd {
            zoom.start()
        }
        zoom.start()



        //val tvLink = viewA.findViewById<TextView>(R.id.tvGitLink)
        val tvLink = binding.tvGitLink
        tvLink.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(tvLink.text.toString())
            startActivity(i)
        }

        //val ivFacCi = viewA.findViewById<ImageView>(R.id.ivFacebCiferri)
        val ivFacCi = binding.ivFacebCiferri
        ivFacCi.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.facebookLinkCiferri))
            startActivity(i)
        }

        //val ivFacCa = viewA.findViewById<ImageView>(R.id.ivFacebCaporro)
        val ivFacCa = binding.ivFacebCaporro
        ivFacCa.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.facebookLinkCaporro))
            startActivity(i)
        }

        //val ivInstaCi = viewA.findViewById<ImageView>(R.id.ivInstaCiferri)
        val ivInstaCi = binding.ivInstaCiferri
        ivInstaCi.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.instaLinkCiferri))
            startActivity(i)
        }

        //val ivInstaCa = viewA.findViewById<ImageView>(R.id.ivInstaCaporro)
        val ivInstaCa = binding.ivInstaCaporro
        ivInstaCa.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.instaLinkCaporro))
            startActivity(i)
        }

        //val ivGitCi = viewA.findViewById<ImageView>(R.id.ivGitCiferri)
        val ivGitCi = binding.ivGitCiferri
        ivGitCi.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.gitLinkCiferri))
            startActivity(i)
        }

        //val ivGitCa = viewA.findViewById<ImageView>(R.id.ivGitCaporro)
        val ivGitCa = binding.ivGitCaporro
        ivGitCa.setOnClickListener {
            val i = Intent(android.content.Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.gitLinkCaporro))
            startActivity(i)
        }

        return binding.root
        //return viewA
    }

}