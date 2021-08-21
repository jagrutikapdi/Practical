package com.practical.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.practical.data.model.Articles
import com.practical.databinding.AdapterNewsBinding
import com.practical.ui.main.view.NewsDetailsActivity
import com.practical.ui.main.view.WebViewActivity
import com.practical.util.BaseBindingAdapter
import com.practical.util.BaseBindingViewHolder
import com.practical.util.Utils


class NewsAdapter(private val activity: Activity) :
    BaseBindingAdapter<Articles>() {
    var binding: AdapterNewsBinding? = null

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding {
        return AdapterNewsBinding.inflate(inflater, parent, false)
    }


    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        binding = holder.binding as AdapterNewsBinding
        binding!!.data = items?.get(position)
        binding!!.tvDate.text = Utils.convertDate(binding!!.data!!.publishedAt)
        binding!!.tvLink.setOnClickListener{
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("link", items?.get(position)?.url)
            activity.startActivity(intent)
        }
        binding!!.root.setOnClickListener{
            val intent = Intent(activity, NewsDetailsActivity::class.java)
            intent.putExtra("data", items?.get(position))
            activity.startActivity(intent)
        }
    }


}


