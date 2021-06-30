package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.UserViewModel
import it.ciferricaporro.memorygame.recyclerView.ScoreboardAdapter
import kotlinx.android.synthetic.main.fragment_scoreboard.*

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

        val tvNoScore = viewSC.findViewById<TextView>(R.id.tvNoScore)
        // provo a capire se ci sono user nel DB
        if(recyclerView.adapter!!.itemCount > 0){
            tvNoScore.isVisible = false
        }

        return viewSC
    }


}