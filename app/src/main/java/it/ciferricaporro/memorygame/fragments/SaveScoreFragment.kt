package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.User
import it.ciferricaporro.memorygame.data.UserViewModel
import java.text.DateFormat


class SaveScoreFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private val args: SaveScoreFragmentArgs by navArgs()
    private val SCORE_CONSTANT: Int = 300000000
    private var score: Long = 0

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

        viewSC.findViewById<TextView>(R.id.tvErrReview).text = "Errors = " + args.err.toString()
        viewSC.findViewById<TextView>(R.id.tvTimeReview).text = "Time = " + args.timeR
        score = (SCORE_CONSTANT/(args.err + args.timeInMillis/2))
        viewSC.findViewById<TextView>(R.id.tvScore).text = "Score = " + score.toString()

        val etPlayerName = viewSC.findViewById<EditText>(R.id.etPlayerName)
        etPlayerName.doAfterTextChanged {
            if(etPlayerName.text.toString().length > 10){
                Toast.makeText(requireContext(), "Player name too long!!", Toast.LENGTH_LONG).show()
                etPlayerName.setText("")
            }
        }

        return viewSC
    }

    private fun insertDataToDB(viewSC:View) {
        val playerName = viewSC.findViewById<EditText>(R.id.etPlayerName).text.toString()
        val errs = args.err
        val time = args.timeR
        val scoreP =  score.toInt()

        val calendar : java.util.Calendar = java.util.Calendar.getInstance()
        val currentDate : String = DateFormat.getDateInstance().format(calendar.time)


        if(inputCheck(playerName)){
            val user = User(0, playerName, time, currentDate, errs, scoreP)
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