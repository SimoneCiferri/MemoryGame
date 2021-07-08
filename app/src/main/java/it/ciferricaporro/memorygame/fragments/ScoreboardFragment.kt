package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.ciferricaporro.memorygame.data.UserViewModel
import it.ciferricaporro.memorygame.databinding.FragmentScoreboardBinding
import it.ciferricaporro.memorygame.recyclerView.ScoreboardAdapter

class ScoreboardFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentScoreboardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScoreboardBinding.inflate(layoutInflater, container, false)
        setRecyclerAndUserVM()
        setUIController()
        return binding.root
    }

    private fun setRecyclerAndUserVM(){
        val adapter = ScoreboardAdapter()
        val recyclerView = binding.recyclerViewScore
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
    }

    private fun setUIController(){
        val tvNoScore = binding.tvNoScore
        if(userViewModel.getC() > 0){
            tvNoScore.isVisible = false
        }
    }


}