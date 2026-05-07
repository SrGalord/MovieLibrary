package com.example.ordoezjaddys.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ordoezjaddys.R
import com.example.ordoezjaddys.databinding.FragmentMovieEditBinding
import com.example.ordoezjaddys.model.Movie
import com.example.ordoezjaddys.viewmodel.MovieViewModel

class MovieEditFragment : Fragment(
    R.layout.fragment_movie_edit
) {

    private var _binding: FragmentMovieEditBinding? = null
    private val binding get() = _binding!!

    private val args: MovieEditFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()

    private var movieId = -1

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieEditBinding.bind(view)

        movieId = args.movieId

        if (movieId != -1) {

            viewModel.getMovie(movieId)
                .observe(viewLifecycleOwner) { movie ->

                    if (movie == null) {
                        findNavController().navigate(
                            R.id.movieListFragment
                        )
                        return@observe
                    }

                    binding.edtTitle.setText(movie.title)
                    binding.edtYear.setText(movie.year.toString())
                    binding.edtGenre.setText(movie.genre)
                    binding.edtRating.setText(movie.rating.toString())
                    binding.checkWatched.isChecked = movie.watched
                }
        }

        binding.btnSave.setOnClickListener {

            val title = binding.edtTitle.text.toString().trim()
            val yearText = binding.edtYear.text.toString().trim()
            val genre = binding.edtGenre.text.toString().trim()
            val ratingText = binding.edtRating.text.toString().trim()

            if (
                title.isEmpty() ||
                yearText.isEmpty() ||
                genre.isEmpty() ||
                ratingText.isEmpty()
            ) {
                return@setOnClickListener
            }

            val movie = Movie(
                id = if (movieId == -1) 0 else movieId,
                title = title,
                year = yearText.toInt(),
                genre = genre,
                rating = ratingText.toFloat(),
                watched = binding.checkWatched.isChecked
            )

            if (movieId == -1) {
                viewModel.insert(movie)
            } else {
                viewModel.update(movie)
            }

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}