package com.example.xtreme.ui.home

import android.view.View
import com.example.xtreme.models.Video

interface RecyclerViewClickListner {

    fun onRecyclerViewItemClick(view: View, video: Video)
}