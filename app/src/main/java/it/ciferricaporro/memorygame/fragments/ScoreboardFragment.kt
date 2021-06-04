package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.UserViewModel
import it.ciferricaporro.memorygame.recyclerView.ScoreboardAdapter

class ScoreboardFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewSC = inflater.inflate(R.layout.fragment_scoreboard, container, false)

        //Recyclerview
        val adapter = ScoreboardAdapter()
        val recyclerView = viewSC.findViewById<RecyclerView>(R.id.recyclerViewScore)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        return viewSC
    }
}