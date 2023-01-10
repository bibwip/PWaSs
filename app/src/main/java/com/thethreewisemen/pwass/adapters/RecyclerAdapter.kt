package com.thethreewisemen.pwass.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.Post

class RecyclerAdapter (
    var posts: ArrayList<Post>
    ) : RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].titel
        holder.name.text = posts[position].naam_poster
        holder.beschrijving.text = posts[position].beschrijving
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updateItems(items : ArrayList<Post>) {
        posts = items
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.postItemTitle)
        val name : TextView = itemView.findViewById(R.id.postItemUserName)
        val beschrijving : TextView = itemView.findViewById(R.id.postItemBeschrijving)
    }
}
