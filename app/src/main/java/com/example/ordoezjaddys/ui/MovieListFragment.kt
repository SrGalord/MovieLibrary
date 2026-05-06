package com.example.ordoezjaddys.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordoezjaddys.R
import com.example.ordoezjaddys.databinding.FragmentMovieListBinding
import com.example.ordoezjaddys.viewmodel.MovieViewModel

class MovieListFragment : Fragment(
    R.layout.fragment_movie_list
) {

    private var _binding:
            FragmentMovieListBinding? = null

    private val binding get() = _binding!!

    private val viewModel:
            MovieViewModel by viewModels()

    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding =
            FragmentMovieListBinding.bind(view)

        // ADAPTER
        adapter = MovieAdapter { movie ->

            val action =
                MovieListFragmentDirections
                    .actionListToDetail(movie.id)

            findNavController().navigate(action)
        }

        // RECYCLER
        binding.recyclerMovies.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerMovies.adapter =
            adapter

        // OBSERVAR PELÍCULAS
        viewModel.movies.observe(
            viewLifecycleOwner
        ) { movies ->

            // CONTADOR
            binding.txtMovies.text =
                "Películas registradas: ${movies.size}"

            // MENSAJE VACÍO
            binding.txtEmpty.visibility =
                if (movies.isEmpty())
                    View.VISIBLE
                else
                    View.GONE

            // LISTA
            adapter.submitList(movies)
        }

        // BOTÓN AGREGAR
        binding.btnAddMovie.setOnClickListener {

            val action =
                MovieListFragmentDirections
                    .actionListToEdit(-1)

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}