package com.practical.ui.main.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practical.R
import com.practical.databinding.ActivityWebviewBinding
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.activity_webview.progressBar


class WebViewActivity : AppCompatActivity() {

    var mBinding:ActivityWebviewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_webview)
        setupUI()

    }

    private fun setupUI() {
        webview.loadUrl(intent.getStringExtra("link"))
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.i("TAG", "Processing webview url click...")
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.i("TAG", "Finished loading URL: $url")
                progressBar.visibility = View.GONE
                webview.visibility = View.VISIBLE
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Log.e("TAG", "Error: $description")
            }
        }
        // this will enable the javascript settings
        webview.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webview.settings.setSupportZoom(true)
        ivBackWeb.setOnClickListener{
            onBackPressed()
        }
    }


}
