package com.practical.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.practical.R
import com.practical.data.api.ApiHelperImpl
import com.practical.data.api.RetrofitBuilder
import com.practical.data.model.NewsData
import com.practical.ui.main.adapter.NewsAdapter
import com.practical.ui.main.intent.MainIntent
import com.practical.ui.main.viewmodel.MainViewModel
import com.practical.ui.main.viewstate.MainState
import com.practical.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter: NewsAdapter? = NewsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        lifecycleScope.launch {
            mainViewModel.newsIntent.send(MainIntent.FetchNews)
        }
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService,
                    this
                ),
                this
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.News -> {
                        progressBar.visibility = View.GONE
                        renderNotificationList(it.data)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun renderNotificationList(data: NewsData) {
        recyclerView.visibility = View.VISIBLE
        data.let { listOfUsers -> listOfUsers.let {
            adapter?.addItems(data.articles)} }
        adapter?.notifyDataSetChanged()
    }

}
