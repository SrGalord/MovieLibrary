package com.example.ordoezjaddys.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ordoezjaddys.R
import com.example.ordoezjaddys.databinding.FragmentMovieDetailBinding
import com.example.ordoezjaddys.model.Movie
import com.example.ordoezjaddys.viewmodel.MovieViewModel

class MovieDetailFragment : Fragment(
    R.layout.fragment_movie_detail
) {

    private var _binding:
            FragmentMovieDetailBinding? = null

    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by viewModels()

    private lateinit var currentMovie: Movie

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailBinding.bind(view)

        val movieId = args.movieId

        viewModel.getMovie(movieId)
            .observe(viewLifecycleOwner) { movie ->

                currentMovie = movie

                binding.txtTitle.text = movie.title

                binding.txtYear.text =
                    "Año: ${movie.year}"

                binding.txtGenre.text =
                    "Género: ${movie.genre}"

                binding.txtRating.text =
                    "Rating: ${movie.rating}"

                binding.checkWatched.isChecked =
                    movie.watched
            }

        binding.checkWatched.setOnCheckedChangeListener {
                _, isChecked ->

            val updatedMovie = currentMovie.copy(
                watched = isChecked
            )

            viewModel.update(updatedMovie)
        }

        binding.btnEdit.setOnClickListener {

            val action =
                MovieDetailFragmentDirections
                    .actionDetailToEdit(currentMovie.id)

            findNavController().navigate(action)
        }

        binding.btnDelete.setOnClickListener {

            viewModel.delete(currentMovie)

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}