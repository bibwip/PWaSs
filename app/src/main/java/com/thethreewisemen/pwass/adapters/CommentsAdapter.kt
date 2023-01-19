package com.thethreewisemen.pwass.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.Comment

class CommentsAdapter(val context: Context, private var comments: MutableList<Comment>, val listener: OnItemClickListener)
    : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (comments.isNotEmpty()) {
            val comment = comments[position]
            holder.userName.text = comment.userName
            holder.text.text = comment.text
            holder.reply.setOnClickListener { listener.onItemClick(comment, position, 0, null)}
            holder.like.setOnClickListener {
                if (!holder.liked) {
                    listener.onItemClick(comment, position, 1, null)
                    holder.liked = true
                    holder.like.setImageResource(R.drawable.ic_like_true)
                } else {
                    holder.liked = false
                    holder.like.setImageResource(R.drawable.ic_like_false)
                }
            }
            if (comment.child.isNotEmpty()){
                holder.child.layoutManager = LinearLayoutManager(context)
                holder.child.adapter = InnerAdapter(comment.child, comment)
            }
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: Comment?, position: Int, type : Int, parent: Comment?)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newCom :ArrayList<Comment>) {
        comments = newCom
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text : TextView = itemView.findViewById(R.id.comContentTv)
        val userName : TextView= itemView.findViewById(R.id.comUsernameTv)
        val child: RecyclerView = itemView.findViewById(R.id.comRecComments)
        val reply: ImageButton = itemView.findViewById(R.id.comReplyBtn)
        val like: ImageButton = itemView.findViewById(R.id.comlikeBtn)
        var liked = false

    }

    private inner class InnerAdapter(private var childCom: List<Comment>, val parent: Comment) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

        init {
            childCom = childCom.sortedBy { com : Comment -> com.likes }.asReversed()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (childCom.isNotEmpty()){
                val comment = childCom[position]
                holder.userName.text = comment.userName
                holder.reply.setOnClickListener { listener.onItemClick(comment, position, 0, null)}
                holder.text.text = comment.text
                holder.like.setOnClickListener {
                    if (!holder.liked) {
                        listener.onItemClick(comment, position, 1, parent)
                        holder.liked = true
                        holder.like.setImageResource(R.drawable.ic_like_true)
                    } else {
                        holder.liked = false
                        holder.like.setImageResource(R.drawable.ic_like_false)
                    }
                }
                if (comment.child.isNotEmpty()){
                    holder.child.layoutManager = LinearLayoutManager(context)
                    holder.child.adapter = InnerAdapter(comment.child, comment)
                }
            }
        }

        override fun getItemCount(): Int {
            return childCom.size
        }

    }
}
