package it.ciferricaporro.memorygame.fragments

import android.animation.AnimatorInflater
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        setUIController()
        return binding.root
    }

    private fun setUIController(){
        val arrow = binding.ivArrow
        val zoom = AnimatorInflater.loadAnimator(requireContext(), R.animator.zoom)
        zoom.setTarget(arrow)
        zoom.duration = 800
        zoom.doOnEnd {
            zoom.start()
        }
        zoom.start()

        val tvLink = binding.tvGitLink
        tvLink.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(tvLink.text.toString())
            startActivity(i)
        }

        val tvFeed = binding.tvFeedback
        tvFeed.setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO)
            i.data = Uri.parse(getString(R.string.email_intent_arg))
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
            startActivity(i)
        }

        val ivFeed = binding.ivFeedback
        ivFeed.setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO)
            i.data = Uri.parse(getString(R.string.email_intent_arg))
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
            startActivity(i)
        }


        val ivFacCi = binding.ivFacebCiferri
        ivFacCi.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.facebookLinkCiferri))
            startActivity(i)
        }

        val ivFacCa = binding.ivFacebCaporro
        ivFacCa.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.facebookLinkCaporro))
            startActivity(i)
        }

        val ivInstaCi = binding.ivInstaCiferri
        ivInstaCi.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.instaLinkCiferri))
            startActivity(i)
        }

        val ivInstaCa = binding.ivInstaCaporro
        ivInstaCa.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.instaLinkCaporro))
            startActivity(i)
        }

        val ivGitCi = binding.ivGitCiferri
        ivGitCi.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.gitLinkCiferri))
            startActivity(i)
        }

        val ivGitCa = binding.ivGitCaporro
        ivGitCa.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(getString(R.string.gitLinkCaporro))
            startActivity(i)
        }
    }

}