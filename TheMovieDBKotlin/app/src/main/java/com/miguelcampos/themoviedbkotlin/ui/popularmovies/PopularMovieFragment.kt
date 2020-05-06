package com.miguelcampos.themoviedbkotlin.ui.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguelcampos.themoviedbkotlin.R
import com.miguelcampos.themoviedbkotlin.viewmodel.MovieViewModel

class PopularMovieFragment : Fragment() {
    private lateinit var movieAdapter: MyPopularMovieRecyclerViewAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_popular_movie_list, container, false)

        // Obtenemos la instancia del ViewModel
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Instaciamos el Adapter
        movieAdapter = MyPopularMovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter // Esta línea en Java era: view.setAdapter(movieAdapter)
            }
        }

        // Conectamos un Observador para obtener la lista de películas populares
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            movieAdapter.setData(it)
        })

        return view
    }
}
