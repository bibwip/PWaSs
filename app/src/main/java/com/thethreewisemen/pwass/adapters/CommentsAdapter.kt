package com.thethreewisemen.pwass.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.Comment

class CommentsAdapter(val context: Context, private var comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (comments.isNotEmpty()) {
            val comment = comments[position]
            holder.userName.text = comment.userName
            holder.text.text = comment.text
            if (comment.child.isNotEmpty()){
                holder.child.layoutManager = LinearLayoutManager(context)
                holder.child.adapter = InnerAdapter(comment.child)
            }
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun getItems() : ArrayList<Comment>{
        return comments
    }

    fun updateItems(newCom :ArrayList<Comment>) {
        comments = newCom
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text : TextView = itemView.findViewById(R.id.comContentTv)
        val userName : TextView= itemView.findViewById(R.id.comUsernameTv)
        val child: RecyclerView = itemView.findViewById(R.id.comRecComments)

    }

    private inner class InnerAdapter(private val childCom: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (childCom.isNotEmpty()){
                val comment = childCom[position]
                holder.userName.text = comment.userName

                holder.text.text = comment.text
                if (comment.child.isNotEmpty()){
                    holder.child.layoutManager = LinearLayoutManager(context)
                    holder.child.adapter = CommentsAdapter(context, comment.child)
                }
            }
        }

        override fun getItemCount(): Int {
            return childCom.size
        }

    }
}
