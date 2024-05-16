package com.akshat.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshat.newsapp.R

import com.akshat.newsapp.adapter.NewsAdapter
import com.akshat.newsapp.databinding.FragmentBusinessBinding

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
class BusinessFragment : Fragment(), OnItemClickListener {

    var binding: FragmentBusinessBinding? = null
    var viewModel: NewsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding!!.progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val news = viewModel?.getBusinessNews(
                Constants.NEWS_COUNTRY,
                Constants.apiKey,
                "business"
            )?.body()
            withContext(Dispatchers.Main){
                binding!!.progressBar.visibility = View.INVISIBLE
                binding?.newsRecycler?.adapter = NewsAdapter(
                    requireContext(),
                    news!!.articles,
                    viewModel!!,
                    this@BusinessFragment
                    )
                binding?.newsRecycler?.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return binding?.root
    }

    override fun onItemClick(position: Int) {
        val i = Intent(requireContext(), NewsWebActivity::class.java)
        i.putExtra("news_url",viewModel?.currentNews)
        activity?.overridePendingTransition(
            R.anim.slide_out_left,
            R.anim.slide_in_right)
        startActivity(i)
    }
}