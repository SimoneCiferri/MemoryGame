package it.ciferricaporro.memorygame.fragments

import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
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
import java.text.SimpleDateFormat
import java.util.*


class SaveScoreFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private val args: SaveScoreFragmentArgs by navArgs()

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

        val etPlayerName = viewSC.findViewById<EditText>(R.id.etPalyerName)
        etPlayerName.doAfterTextChanged {
            if(etPlayerName.text.toString().length > 4){
                Toast.makeText(requireContext(), "Player name too long!!", Toast.LENGTH_LONG).show()
                etPlayerName.setText("")
            }
        }

        return viewSC
    }

    private fun insertDataToDB(viewSC:View) {
        val playerName = viewSC.findViewById<EditText>(R.id.etPalyerName).text.toString()
        val errs = args.err
        val time = args.timeR
        val sdf = SimpleDateFormat("dd/mm/yyyy")
        val currentDate = sdf.format(Date())
        Toast.makeText(requireContext(), currentDate, Toast.LENGTH_LONG).show()

        if(inputCheck(playerName)){
            val user = User(0, playerName, time, currentDate, errs)
            userViewModel.addUser(user)
            //Toast.makeText(requireContext(), "Score Saved!", Toast.LENGTH_LONG).show()
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