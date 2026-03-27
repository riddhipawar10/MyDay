package com.example.myday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myday.R
import com.example.myday.data.remote.response.Article

class ArticleAdapter(private val list: List<Article>?): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_post, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = list?.get(position)
        holder.titleTxt.text = current?.title.toString()
        holder.bioTxt.text = current?.body
        holder.desTxt.text = current?.description
    }


    inner class ArticleViewHolder(itemView: View): ViewHolder(itemView){
        val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        val desTxt = itemView.findViewById<TextView>(R.id.desTxt)
        val bioTxt = itemView.findViewById<TextView>(R.id.bioTxt)
    }
}