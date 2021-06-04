package it.ciferricaporro.memorygame.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.User
import java.util.*

class ScoreboardAdapter: RecyclerView.Adapter<ScoreboardAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.score_raw, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvScoreName).text = currentItem.player
        holder.itemView.findViewById<TextView>(R.id.tvScoreErrors).text = currentItem.errors.toString()
        holder.itemView.findViewById<TextView>(R.id.tvScoreTime).text = currentItem.time
        holder.itemView.findViewById<TextView>(R.id.tvScoreData).text = currentItem.Data
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}