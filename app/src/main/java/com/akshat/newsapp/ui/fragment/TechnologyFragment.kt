package com.akshat.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshat.newsapp.adapter.NewsAdapter
import com.akshat.newsapp.databinding.FragmentTechnologyBinding

import com.akshat.newsapp.listners.OnItemClickListener
import com.akshat.newsapp.ui.activity.NewsWebActivity
import com.akshat.newsapp.util.Constants
import com.akshat.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TechnologyFragment : Fragment(), OnItemClickListener {

    var binding: FragmentTechnologyBinding? = null
    var viewModel: NewsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTechnologyBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding!!.progressBar.setVisibility(View.VISIBLE)

        CoroutineScope(Dispatchers.IO).launch {
            val news = viewModel?.getTechnologyNews(
                Constants.NEWS_COUNTRY,
                Constants.apiKey,
                "technology"
            )?.body()
            Log.d(Constants.MYTAG,news.toString())
            withContext(Dispatchers.Main){
                binding!!.progressBar.setVisibility(View.INVISIBLE)
                binding?.newsRecycler?.adapter = NewsAdapter(requireContext(),news!!.articles,viewModel!!,this@TechnologyFragment)
                binding?.newsRecycler?.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return binding?.root
    }

    override fun onItemClick(position: Int) {
        val i = Intent(requireContext(), NewsWebActivity::class.java)
        i.putExtra("news_url",viewModel?.currentNews)
        startActivity(i)
    }
}