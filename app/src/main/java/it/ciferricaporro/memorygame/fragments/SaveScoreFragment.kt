package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.User
import it.ciferricaporro.memorygame.data.UserViewModel


class SaveScoreFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewSC = inflater.inflate(R.layout.fragment_save_score, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewSC.findViewById<Button>(R.id.btnSave).setOnClickListener {
            insertDataToDB(viewSC)
        }

        return viewSC
    }

    private fun insertDataToDB(viewSC:View) {
        val playerName = viewSC.findViewById<EditText>(R.id.etPalyerName).text.toString()

        if(inputCheck(playerName)){
            val user = User(0, playerName, 10.0, 10)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Score Saved!", Toast.LENGTH_LONG).show()
            val navToScore = SaveScoreFragmentDirections.actionSaveScoreFragmentToScoreboardFragment()
            Navigation.findNavController(viewSC).navigate(navToScore)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String) :Boolean{
        return !(TextUtils.isEmpty(firstName))
    }

}