package com.practical.ui.main.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practical.R
import com.practical.databinding.ActivityZoomImageBinding
import kotlinx.android.synthetic.main.activity_zoom_image.*


class ZoomImageActivity : AppCompatActivity() {

    private var scaleGestureDetector: ScaleGestureDetector? = null
    var mBinding:ActivityZoomImageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_zoom_image)
        setupUI()
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener(ivZoom))

    }

    private fun setupUI() {
        mBinding?.data = intent.getStringExtra("url")

    }
    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    private class ScaleListener( ivZoom: ImageView) : SimpleOnScaleGestureListener() {
        var mScaleFactor = 1.0f
        var ivZoom = ivZoom
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            ivZoom.setScaleX(mScaleFactor)
            ivZoom.setScaleY(mScaleFactor)
            return true
        }
    }

}
