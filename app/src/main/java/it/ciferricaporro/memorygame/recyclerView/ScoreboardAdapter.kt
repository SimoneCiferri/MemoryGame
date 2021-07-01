package it.ciferricaporro.memorygame.recyclerView

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import it.ciferricaporro.memorygame.R
import it.ciferricaporro.memorygame.data.User
import it.ciferricaporro.memorygame.data.UserViewModel

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
        holder.itemView.findViewById<TextView>(R.id.tvRealScore).text = currentItem.score.toString()
        if(currentItem.achievements == 0){
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar1).setImageResource(R.drawable.ach_star)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar2).setImageResource(R.drawable.ach_star)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar3).setImageResource(R.drawable.ach_star)
            holder.itemView.findViewById<ImageView>(R.id.ivBestPlayerITA).isVisible = false
        }
        if(currentItem.achievements == 1){
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar1).setImageResource(R.drawable.ach_star_reached)
                }
        if(currentItem.achievements == 2){
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar1).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar2).setImageResource(R.drawable.ach_star_reached)
        }
        if(currentItem.achievements == 3){
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar1).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar2).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar3).setImageResource(R.drawable.ach_star_reached)
        }
        if(currentItem.achievements == 4){
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar1).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar2).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivAchStar3).setImageResource(R.drawable.ach_star_reached)
            holder.itemView.findViewById<ImageView>(R.id.ivBestPlayerITA).isVisible = true
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}