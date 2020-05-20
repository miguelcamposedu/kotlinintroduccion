package com.androidavanzado.popcorn.di

import com.androidavanzado.popcorn.api.NetworkModule
import com.androidavanzado.popcorn.common.SharedPreferencesModule
import com.androidavanzado.popcorn.ui.movies.MovieListFragment
import com.androidavanzado.popcorn.ui.people.PeopleListFragment
import com.androidavanzado.popcorn.ui.people_detail.PeopleDetailFragment
import com.androidavanzado.popcorn.ui.people_detail.PersonDetailScrollingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class, SharedPreferencesModule::class])
interface ApplicationComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(peopleListFragment: PeopleListFragment)
    fun inject(peopleDetailScrollingActivity: PersonDetailScrollingActivity)
}