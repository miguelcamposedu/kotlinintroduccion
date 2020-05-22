package com.androidavanzado.popcorn.ui.people_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.androidavanzado.popcorn.R
import com.androidavanzado.popcorn.common.Resource
import com.androidavanzado.popcorn.di.MyApp
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_people_detail.*

class PeopleDetailFragment @Inject constructor(var peopleDetailViewModel: PeopleDetailViewModel): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_people_detail, container, false)

        // Observer for personMovies
        peopleDetailViewModel.personInfoDetail.observe(viewLifecycleOwner, Observer { response ->
            Log.i("MOVIES", "sharedViewModel en PeopleDetailFragment: $peopleDetailViewModel")

            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { personDetail ->
                        text_view_bio.text = personDetail.biography
                        text_view_place_birth.text = personDetail.birthday
                        text_view_birthday.text = personDetail.place_of_birth
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "${message}", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        return view
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        text_view_bio.visibility = View.VISIBLE
        text_view_place_birth.visibility = View.VISIBLE
        text_view_birthday.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        text_view_bio.visibility = View.INVISIBLE
        text_view_place_birth.visibility = View.INVISIBLE
        text_view_birthday.visibility = View.INVISIBLE
    }

}
