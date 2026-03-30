package com.example.myday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myday.R
import com.example.myday.data.remote.response.Article

class ArticleAdapter(private val list: List<Article>?): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_post, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = list?.get(position)
        holder.titleTxt.text = current?.title
        holder.desTxt.text = current?.description
        holder.bodyTxt.text = current?.body
        holder.authorTxt.text = current?.author?.username ?: current?.userId
    }


    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val desTxt: TextView = itemView.findViewById(R.id.desTxt)
        val bodyTxt: TextView = itemView.findViewById(R.id.bioTxt)
        val authorTxt: TextView = itemView.findViewById(R.id.authorTxt)
    }
}