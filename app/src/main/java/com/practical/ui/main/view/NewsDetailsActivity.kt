package com.practical.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practical.R
import com.practical.data.model.Articles
import com.practical.databinding.ActivityNewsDetailsBinding
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    var mBinding:ActivityNewsDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_news_details)
        setupUI()

    }

    private fun setupUI() {
        mBinding?.data = intent.getSerializableExtra("data") as Articles
        ivBack.setOnClickListener{
            onBackPressed()
        }
        imageDetails.setOnClickListener{
            val intent = Intent(this, ZoomImageActivity::class.java)
            intent.putExtra("url", mBinding?.data?.urlToImage)
            startActivity(intent)
        }
    }


}
