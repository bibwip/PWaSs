package com.thethreewisemen.pwass.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.Post


class RecyclerAdapter (var posts: ArrayList<Post>, val main : MainActivity, val listener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].titel
        holder.name.text = posts[position].naam_poster
        holder.beschrijving.text = posts[position].beschrijving
        holder.datum.text = posts[position].getDate()
        holder.card.setOnClickListener { listener.onItemClick(posts[position], position) }
        if (main.hasCustomTheme) holder.card.background.setTint(Color.parseColor(main.colorPost))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updateItems(items : ArrayList<Post>) {
        posts = items
        notifyDataSetChanged()
    }

    // parent activity will implement this method to respond to click events
    interface OnItemClickListener {
        fun onItemClick(item: Post?, position: Int)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.postItemTitle)
        val name : TextView = itemView.findViewById(R.id.postItemUserName)
        val beschrijving : TextView = itemView.findViewById(R.id.postItemBeschrijving)
        val card : CardView= itemView.findViewById(R.id.postCard)
        val datum : TextView = itemView.findViewById(R.id.datumPost)

//        init {
//
//        }

    }
}
