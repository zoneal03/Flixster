package com.example.project3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieViewAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItem: Movie? = null
        val mImage: ImageView = itemView.findViewById(R.id.movie_image)
        val mDescription: TextView = itemView.findViewById(R.id.movie_description)
        val mTitle: TextView = itemView.findViewById(R.id.movie_title)

    }

    private fun fix(url : String): String {//may have to change if  image dont work
        return "https://image.tmdb.org/t/p/w500/$url"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mDescription.text = movie.description
        holder.mTitle.text = movie.title.toString()

        Glide.with(holder.itemView)
            .load(fix(movie.movieImageUrl.toString()))
            .centerInside()
            .into(holder.mImage)

    }
}