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

    private var _binding:
            FragmentMovieEditBinding? = null

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

        // EDITAR
        if (movieId != -1) {

            viewModel.getMovie(movieId)
                .observe(viewLifecycleOwner) {

                    binding.edtTitle.setText(it.title)

                    binding.edtYear
                        .setText(it.year.toString())

                    binding.edtGenre.setText(it.genre)

                    binding.edtRating
                        .setText(it.rating.toString())

                    binding.checkWatched.isChecked =
                        it.watched
                }
        }

        binding.btnSave.setOnClickListener {

            val movie = Movie(

                id = if (movieId == -1) 0
                else movieId,

                title =
                    binding.edtTitle.text.toString(),

                year =
                    binding.edtYear.text
                        .toString()
                        .toInt(),

                genre =
                    binding.edtGenre.text.toString(),

                rating =
                    binding.edtRating.text
                        .toString()
                        .toFloat(),

                watched =
                    binding.checkWatched.isChecked
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