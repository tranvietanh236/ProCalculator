package com.V1solution.procaculator.rating

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.travietnanh.procaculator.R

class RatingDialog constructor(mContext: Context) : Dialog(mContext, R.style.CustomDialogTheme) {
    private var onPressListener: OnPressListener? = null
    private val tv_title: TextView
    private val tv_content: TextView
    private val iv_back: ImageView
    private val rtb: RatingBar
    private val tv_cancel: TextView
    private val tv_gotit: TextView
    private val tv_submit: TextView
    private val context: Context
    private var s = 5

    init {
        this.context = mContext
        setContentView(R.layout.dialog_rating)
        val attributes = window!!.attributes
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = attributes
        window!!.setSoftInputMode(16)

        tv_title = findViewById(R.id.tv_title)
        tv_content = findViewById(R.id.tv_content)
        iv_back = findViewById(R.id.ic_close)
        rtb = findViewById(R.id.ratingbar)
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_submit = findViewById(R.id.tv_submit)
        tv_gotit = findViewById(R.id.tv_gotit)
        rtb.rating = 5.0f

        onClick()
        onChangeRating()
    }

    fun init(context: Context?, onPress: OnPressListener?) {
        this.onPressListener = onPress
    }

    fun onChangeRating() {
        rtb.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar: RatingBar?, rating: Float, fromUser: Boolean ->
                val getRating = rtb.rating.toString()
                s = when (getRating) {
                    "1.0" -> 1
                    "2.0" -> 2
                    "3.0" -> 3
                    "4.0" -> 4
                    "5.0" -> 5
                    else -> 0
                }
            }
    }
    fun onClick() {
        tv_submit.setOnClickListener {
            if (rtb.rating == 0.0f) {
                Toast.makeText(
                    context,
                    "Please feedback",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (rtb.rating <= 3.0f) {
                onPressListener?.send(s)
            }
            if (rtb.rating > 3.0f) {
                onPressListener?.rating(s)
            }

            tv_title.text = context.getString(R.string.Thank_You)
            tv_content.text =
                context.getString(R.string.Thank_you_for_taking_the_time_to_rate_us_i_m_really_appreciate_that)
            rtb.visibility = View.GONE
            tv_gotit.visibility = View.VISIBLE
            tv_cancel.visibility = View.GONE
            tv_submit.visibility = View.GONE
        }

        tv_cancel.setOnClickListener {
            onPressListener!!.later()
        }

        iv_back.setOnClickListener {
            onPressListener!!.cancel()
        }

        tv_gotit.setOnClickListener {
            onPressListener!!.gotit()
        }
    }
    interface OnPressListener {
        fun send(s: Int)
        fun rating(s: Int)
        fun cancel()
        fun later()
        fun gotit()
    }
}