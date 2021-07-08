package it.ciferricaporro.memorygame.fragments

import android.annotation.SuppressLint
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
import it.ciferricaporro.memorygame.databinding.FragmentSaveScoreBinding
import java.text.DateFormat


class SaveScoreFragment : Fragment() {

    private lateinit var binding: FragmentSaveScoreBinding
    private lateinit var userViewModel: UserViewModel
    private val args: SaveScoreFragmentArgs by navArgs()
    private val scoreConstant: Int = 30000000
    private var score: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveScoreBinding.inflate(layoutInflater, container, false)
        setUiController()
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    private fun setUiController(){

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnSave.setOnClickListener {
            insertDataToDB()
        }
        binding.tvErrReview.text = getString(R.string.errorsInfo) + args.err.toString()
        binding.tvTimeReview.text = getString(R.string.timeInfo) + args.timeR
        score = (scoreConstant/(args.timeInMillis/3 + 100*(args.err)))
        binding.tvScore.text = getString(R.string.scoreShow) + score.toString()
        val etPlayerName = binding.etPlayerName
        etPlayerName.doAfterTextChanged {
            if(etPlayerName.text.toString().length > 6){
                Toast.makeText(requireContext(), getString(R.string.playerNameTooLong), Toast.LENGTH_LONG).show()
                etPlayerName.setText("")
            }
        }
    }


    private fun insertDataToDB() {
        val playerName = binding.etPlayerName.text.toString()
        val errs = args.err
        val time = args.timeR
        val scoreP =  score.toInt()
        val achiev = checkAchievements(scoreP)

        val calendar : java.util.Calendar = java.util.Calendar.getInstance()
        val currentDate : String = DateFormat.getDateInstance().format(calendar.time)


        if(inputCheck(playerName)){
            val user = User(0, playerName, time, currentDate, errs, scoreP, achiev)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), getString(R.string.scoreSaved), Toast.LENGTH_LONG).show()
            val navToScore = SaveScoreFragmentDirections.actionSaveScoreFragmentToScoreboardFragment()
            Navigation.findNavController(binding.root).navigate(navToScore)
        }else{
            Toast.makeText(requireContext(), getString(R.string.fillAllFieldsWarning), Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String) :Boolean{
        return !(TextUtils.isEmpty(firstName))
    }

    private fun checkAchievements(score: Int):Int{
        if(score < 5500){
            return 0
        }
        if(score in 5501..6999){
            return 1
        }
        if(score in 7001..8499){
            return 2
        }
        if(score in 8501..9999){
            return 3
        }
        else return 4
    }

}