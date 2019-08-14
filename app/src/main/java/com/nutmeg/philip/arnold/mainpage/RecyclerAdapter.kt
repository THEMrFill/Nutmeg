package com.nutmeg.philip.arnold.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nutmeg.philip.arnold.R
import com.nutmeg.philip.arnold.retrofit.data.PostData

class RecyclerAdapter(val posts: ArrayList<PostData>): RecyclerView.Adapter<RecyclerAdapter.PostDataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDataHolder {
        return PostDataHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false))
    }

    override fun getItemCount(): Int = Math.min(posts.size, 10)

    override fun onBindViewHolder(holder: PostDataHolder, position: Int) {
        holder.Bind(posts.get(position))
    }

    fun UpdateData(newList: ArrayList<PostData>) {
        posts.clear()
        posts.addAll(newList)
        notifyDataSetChanged()
    }

    class PostDataHolder(view: View): RecyclerView.ViewHolder(view) {
        val user: TextView
        val title: TextView
        val body: TextView
        init {
            user = view.findViewById(R.id.user)
            title = view.findViewById(R.id.title)
            body = view.findViewById(R.id.body)
        }

        fun Bind(post: PostData) {
            user.text = post.user?.name
            title.text = post.title
            body.text = post.body.trim()
        }
    }
}