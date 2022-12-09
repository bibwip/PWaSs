package com.thethreewisemen.pwass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecAdap.VieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_view,parent, false)


    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RecAdap.VieHolder, position: Int) {}

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemContent: TextView

        init {
            itemTitle = itemView.findViewById(R.id.test_title)
            itemContent = itemView.findViewById(R.id.test_content)
        }
    }
}