package com.example.xtreme.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xtreme.R
import com.example.xtreme.models.Video
import kotlinx.android.synthetic.main.video_layout.view.*


class VideoAdapter(private val videos : List<Video>, private val listener: RecyclerViewClickListner
    ) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.video_layout, parent, false)
        )
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]

        holder.view.textViewTitle.text = video.title
        holder.view.textViewLanguage.text = video.language
        holder.view.textViewIsNew.visibility = if (video.isNew) View.VISIBLE else View.INVISIBLE

        holder.view.cardView_id.setOnClickListener(View.OnClickListener {
//            val intent = Intent(context, TryActivity::class.java)
//            context?.startActivity(intent)
            listener.onRecyclerViewItemClick(holder.view.cardView_id, videos[position])

//            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        })


    }



    class VideoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

//

//        init {
//            view.setOnClickListener{
//                val intent = Intent(view.context, MainActivity::class.java)
//                view.context.startActivity(intent)
//            }
//        }

//        fun setData(context: Context?, pos: Int) {
//
//        }

}

//        init {
//            view.setOnClickListener{
//                println("TEST ANOTHER ANOTHER")
////                val position  =  adapterPosition
////                Snackbar.make(v, "Click detected on item $position",
////                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
//            }
//        }

    }


    //    var tv_video_title: TextView? = null
//    var img_thumbnail: ImageView? = null
    //var cardView: CardView? = null


//
//fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//    holder.tv_book_title.setText(mData.get(position).getTitle())
//    holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail())
//    holder.cardView.setOnClickListener(View.OnClickListener {
//        val intent = Intent(mContext, Book_Activity::class.java)
//        // passing data to the book activity
//        intent.putExtra("Title", mData.get(position).getTitle())
//        intent.putExtra("Description", mData.get(position).getDescription())
//        intent.putExtra("Thumbnail", mData.get(position).getThumbnail())
//        // start the activity
//        mContext.startActivity(intent)
//    })
//}



