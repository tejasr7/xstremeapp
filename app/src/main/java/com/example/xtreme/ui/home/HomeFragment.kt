package com.example.xtreme.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xtreme.R
import com.example.xtreme.VideoActivity
import com.example.xtreme.models.Video
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), RecyclerViewClickListner {

    private lateinit var homeViewModel: HomeViewModel


    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.txtDescription)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showVideo()
    }

    private val videos = listOf(
        Video(1, R.drawable.captain_marvel, true, "Captain Marvel", "Hindi", "Game"),
        Video(2, R.drawable.fortnite, false, "Captain Marvel", "Hindi", "Game"),
        Video(3, R.drawable.gtav, true, "Captain Marvel", "Hindi", "Game"),
        Video(4, R.drawable.league_of_legends, false, "Captain Marvel", "Hindi", "Game"),
        Video(5, R.drawable.minecraft, true, "Captain Marvel", "Hindi", "Game"),
        Video(6, R.drawable.zynga, false, "Captain Marvel", "Hindi", "Game"),
        Video(7, R.drawable.captain_marvel, false, "Captain Marvel", "Hindi", "Game")
    )

     fun showVideo() {
        recyclerViewVideo.layoutManager = LinearLayoutManager(activity)
        recyclerViewVideo.adapter = VideoAdapter(videos, this)

    }

    override fun onRecyclerViewItemClick(view: View, video: Video) {
        when(view.id) {
            R.id.cardView_id -> {
                val intent = Intent(requireContext(), VideoActivity::class.java)
                requireContext().startActivity(intent)
            }
        }
    }

}