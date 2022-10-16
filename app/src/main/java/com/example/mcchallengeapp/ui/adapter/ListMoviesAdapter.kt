package com.example.mcchallengeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mcchallengeapp.R
import com.example.mcchallengeapp.data.entity.MoviesResponse
import com.example.mcchallengeapp.databinding.ItemMovieBinding
import com.example.mcchallengeapp.utils.IMAGE_BASE_URL

class ListMoviesAdapter : RecyclerView.Adapter<ListMoviesAdapter.ViewHolder>() {

    lateinit var onClick: (MoviesResponse) -> Unit
    private var dataSet = emptyList<MoviesResponse>()
    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bindResponse(item, onClick)
    }

    override fun getItemCount() = dataSet.size

    fun addMovies(items: List<MoviesResponse>?) {
        if (items != null) {
            this.dataSet = items
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = ItemMovieBinding.bind(v)
        fun bindResponse(movie: MoviesResponse, onClick: (MoviesResponse) -> Unit) =
            with(itemView) {

                val imageUrl = "$IMAGE_BASE_URL${movie.posterPath}"
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_search)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.itemImage)

                binding.itemText.text = movie.title
                setOnClickListener { onClick(movie) }
            }
    }
}
