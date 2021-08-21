package com.practical.ui.main.view

import android.os.Bundle
import android.util.Log
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
import com.practical.databinding.ActivityWebviewBinding
import com.practical.ui.main.adapter.NewsAdapter
import com.practical.ui.main.intent.MainIntent
import com.practical.ui.main.viewmodel.MainViewModel
import com.practical.ui.main.viewstate.MainState
import com.practical.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class WebViewActivity : AppCompatActivity() {

    var mBinding:ActivityWebviewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_webview)
        setupUI()

    }

    private fun setupUI() {
        Log.d("TAG","Link is ==>> "+intent.getStringExtra("link"))
        webview.loadUrl(intent.getStringExtra("link"))

        // this will enable the javascript settings
        webview.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webview.settings.setSupportZoom(true)
        ivBackWeb.setOnClickListener{
            onBackPressed()
        }
    }


}
