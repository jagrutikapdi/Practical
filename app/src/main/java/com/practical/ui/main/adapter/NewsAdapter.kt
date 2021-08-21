package com.practical.ui.main.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.practical.data.model.Articles
import com.practical.databinding.AdapterNewsBinding
import com.practical.util.BaseBindingAdapter
import com.practical.util.BaseBindingViewHolder


class NewsAdapter(private val activity: Activity) :
    BaseBindingAdapter<Articles>() {
    var binding: AdapterNewsBinding? = null

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding {
        return AdapterNewsBinding.inflate(inflater, parent, false)
    }


    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        binding = holder.binding as AdapterNewsBinding
        binding!!.data = items?.get(position)
    }


}


