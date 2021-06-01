package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.ciferricaporro.memorygame.R


class MemoryGame : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewMG = inflater.inflate(R.layout.fragment_memory_game, container, false)

        return viewMG
    }

}