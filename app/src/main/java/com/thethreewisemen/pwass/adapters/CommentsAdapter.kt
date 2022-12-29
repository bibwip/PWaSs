package com.thethreewisemen.pwass.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.Comment

class CommentsAdapter(val context: Context, private val comments: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.userName.text = comment.userName
        holder.text.text = comment.text
        if (comment.child.isNotEmpty()){
            holder.child.layoutManager = LinearLayoutManager(context)
            holder.child.adapter = InnerAdapter(comment.child)
        }

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text : TextView
        var userName: TextView
        var child : RecyclerView

        init {
            text = itemView.findViewById(R.id.testComText)
            userName = itemView.findViewById(R.id.testComUser)
            child = itemView.findViewById(R.id.testComList)
        }
    }

    inner  class InnerAdapter(val childCom: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val comment = childCom[position]
            holder.userName.text = comment.userName
            holder.text.text = comment.text
            if (comment.child.isNotEmpty()){
                holder.child.layoutManager = LinearLayoutManager(context)
                holder.child.adapter = CommentsAdapter(context, comment.child)
            }

        }

        override fun getItemCount(): Int {
            return childCom.size
        }

    }
}
