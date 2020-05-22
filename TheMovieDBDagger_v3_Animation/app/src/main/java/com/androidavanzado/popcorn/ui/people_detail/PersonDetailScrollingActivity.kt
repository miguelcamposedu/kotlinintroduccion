package com.androidavanzado.popcorn.ui.people_detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.androidavanzado.popcorn.R
import com.androidavanzado.popcorn.common.Constants
import com.androidavanzado.popcorn.di.MyApp
import kotlinx.android.synthetic.main.activity_person_detail_scrolling.*
import javax.inject.Inject

class PersonDetailScrollingActivity : AppCompatActivity() {

    @Inject lateinit var peopleDetailViewModel: PeopleDetailViewModel
    @Inject lateinit var peopleDetailFragment: PeopleDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail_scrolling)
        setSupportActionBar(toolbar)

        (this.applicationContext as MyApp).appComponent.inject(this)

        supportFragmentManager.beginTransaction().add(R.id.contenedor, peopleDetailFragment).commit()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val extras: Bundle? = intent.extras
        // val id = extras?.getInt(Constants.EXTRA_PERSON_ID)
        val id = 87456292
        val photo = extras?.getString(Constants.EXTRA_PERSON_PHOTO)
        val name = extras?.getString(Constants.EXTRA_PERSON_NAME)

        title = name

        toolbarImage.load(Constants.IMAGE_BASE_URL + photo) {
            crossfade(true)
            placeholder(R.drawable.ic_bg_circle_empty)
        }

        id?.let { peopleDetailViewModel.setSelectedPerson(it) }

    }
}
