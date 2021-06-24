package com.example.myplaylist.homeScreen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaylist.R
import com.example.myplaylist.dataObjects.Item
import com.example.myplaylist.dataObjects.ItemX
import com.squareup.picasso.Picasso

class SongListRecyclerView(var arrayList: ArrayList<ItemX>, val context: Context, val listener: OnItemSongClickListener):
    RecyclerView.Adapter<SongListRecyclerView.ViewHolder>() {

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val iv_songPic: ImageView
        val tx_songName: TextView



        init {

            iv_songPic = view.findViewById(R.id.imageViewSong)
            tx_songName = view.findViewById(R.id.textViewSong)
            //pass the listener
            itemView.setOnClickListener(this)

        }


       //handle the songs selected
       override fun onClick(v: View?) {
           val position: Int = absoluteAdapterPosition
           if (position != RecyclerView.NO_POSITION) {
               listener.onItemSongClick(position)
           }
       }
    }


    interface OnItemSongClickListener {
        fun onItemSongClick(i: Int)

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_song, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = this.arrayList[position]
        //get the image using picasso library
        Picasso.get().load(currentItem.snippet.thumbnails.high.url).into(viewHolder.iv_songPic)
        viewHolder.tx_songName.text = (currentItem.snippet.title)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }



}