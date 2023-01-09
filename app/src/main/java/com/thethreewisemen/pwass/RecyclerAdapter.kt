package com.thethreewisemen.pwass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.FallbackServiceBroker
import com.thethreewisemen.pwass.objects.Post

class RecyclerAdapter (
    var posts: List<Post>
    ) : RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].titel
        holder.name.text = posts[position].naam_poster
        holder.content.text = posts[position].beschrijving
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.test_title)
        val name : TextView = itemView.findViewById(R.id.user_name)
        val content : TextView = itemView.findViewById(R.id.test_content)
    }
}
