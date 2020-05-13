package com.androidavanzado.popcorn.di

import com.androidavanzado.popcorn.MainActivity
import com.androidavanzado.popcorn.api.NetworkModule
import com.androidavanzado.popcorn.repository.TheMovieDBRepository
import com.androidavanzado.popcorn.ui.dashboard.DashboardFragment
import com.androidavanzado.popcorn.ui.movies.MovieListFragment
import com.androidavanzado.popcorn.ui.movies.MovieViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(dashboardFragment: DashboardFragment)
}