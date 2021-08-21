package com.practical.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.practical.R
import com.practical.data.api.ApiHelperImpl
import com.practical.data.api.RetrofitBuilder
import com.practical.data.model.Articles
import com.practical.data.model.NewsData
import com.practical.databinding.ActivityNewsDetailsBinding
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
class NewsDetailsActivity : AppCompatActivity() {

    var mBinding:ActivityNewsDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_news_details)
        setupUI()

    }

    private fun setupUI() {
        mBinding?.data = intent.getSerializableExtra("data") as Articles
    }


}
