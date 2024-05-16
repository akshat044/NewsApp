package com.akshat.newsapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akshat.newsapp.R
import com.akshat.newsapp.databinding.ActivityNewsBinding

import com.akshat.newsapp.ui.fragment.BusinessFragment
import com.akshat.newsapp.ui.fragment.EntertainmentFragment
import com.akshat.newsapp.ui.fragment.HealthFragment
import com.akshat.newsapp.ui.fragment.ScienceFragment
import com.akshat.newsapp.ui.fragment.TechnologyFragment
import com.akshat.newsapp.ui.fragment.TopHeadlineFragment
import com.akshat.newsapp.util.Constants
import com.akshat.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    private lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this@NewsActivity,binding.drawerLayout, R.string.nav_open,R.string.nav_close
        )

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        actionBarDrawerToggle!!.getDrawerArrowDrawable().setColor(getColor(R.color.black))

        if (Constants.isNetworkAvailable(this)) {
            Constants.goToFragment(supportFragmentManager, TopHeadlineFragment())
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
        }

        binding.entertainment.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, EntertainmentFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.science.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, ScienceFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.sports.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, EntertainmentFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.technology.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, TechnologyFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.business.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, BusinessFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.health.setOnClickListener {
            if (Constants.isNetworkAvailable(this)) {
                Constants.goToFragment(supportFragmentManager, HealthFragment())
            } else {
                Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show()
            }
        }
//
//        binding.flag.setOnClickListener {
//            Toast.makeText(this@NewsActivity, "Clicked", Toast.LENGTH_SHORT).show()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            Toast.makeText(this, "Flag clicked", Toast.LENGTH_SHORT).show()
            true
        } else super.onOptionsItemSelected(item)
    }



}