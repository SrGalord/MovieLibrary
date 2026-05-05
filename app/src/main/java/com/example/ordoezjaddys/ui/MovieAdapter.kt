package com.example.ordoezjaddys.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordoezjaddys.databinding.ItemMovieBinding
import com.example.ordoezjaddys.model.Movie

class MovieAdapter(
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            // TITULO
            binding.txtTitle.text =
                movie.title

            // GENERO
            binding.txtGenre.text =
                movie.genre

            // AÑO
            binding.txtYear.text =
                movie.year.toString()

            // ESTADO
            binding.txtWatched.text =
                if (movie.watched)
                    "✅ Vista"
                else
                    "⏳ Pendiente"

            // CLICK
            binding.root.setOnClickListener {

                onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {

        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {

        return movies.size
    }

    fun submitList(list: List<Movie>) {

        movies = list

        notifyDataSetChanged()
    }
}