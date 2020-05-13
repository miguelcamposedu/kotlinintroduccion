package com.androidavanzado.popcorn.ui.movies

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidavanzado.popcorn.R
import com.androidavanzado.popcorn.di.MyApp
import com.androidavanzado.popcorn.api.response.Movie
import javax.inject.Inject

class MovieListFragment : Fragment() {
    //TODO Inyectamos la dependencia del ViewModel sobre la propiedad (Setter Injection)
    // Opción 1
    @Inject lateinit var movieViewModel: MovieViewModel
    @Inject lateinit var sharedPref: SharedPreferences

    private lateinit var movieAdapter: MovieRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO Defino el punto de partida del gráfico de la aplicación
        // si quisiéramos hacerlo en un Activity la sintaxis varía ligeramente
        // sería así:
        // (applicationContext as MyApp).appComponent.inject(this)
        (activity?.applicationContext as MyApp).appComponent.inject(this)

        val highScore = sharedPref.getInt("ejemplo", 1)
        Toast.makeText(activity, "Ejemplo $highScore", Toast.LENGTH_LONG).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        movieAdapter = MovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }

        // Observer for popularMovies
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })

        return view
    }

}
