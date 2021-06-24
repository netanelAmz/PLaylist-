package com.example.myplaylist.homeScreen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaylist.R
import com.example.myplaylist.dataObjects.Item
import com.example.myplaylist.dataObjects.ItemX
import com.squareup.picasso.Picasso

class PlayListRecyclerView(
    var arrayList: ArrayList<Item>,
    val context: Context,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<PlayListRecyclerView.ViewHolder>(),
    SongListRecyclerView.OnItemSongClickListener {


    private var pos: Int = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val iv_mainPic: ImageView
        val tx_mainPlay: TextView
        val recyclerView: RecyclerView


        init {
            iv_mainPic = view.findViewById(R.id.imageViewPlayerList)
            tx_mainPlay = view.findViewById(R.id.TextViewPlayerList)
            recyclerView = view.findViewById(R.id.recycler_view_songs)

            //pass the listener
            itemView.setOnClickListener(this)


        }

        //handle the inner recycler view to open and close on selected
        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
                pos = position
                if (recyclerView.isVisible) {
                    recyclerView.visibility = View.GONE
                    pos = RecyclerView.NO_POSITION
                    notifyItemChanged(position)
                } else {
                    recyclerView.visibility = View.VISIBLE

                }
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(i: Int)
        fun onSongClick(i: Int, pos: Int)

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_card, viewGroup, false),
        )

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = this.arrayList[position]
        //get the image using picasso library
        Picasso.get().load(currentItem.snippet.thumbnails.high.url).into(viewHolder.iv_mainPic)
        viewHolder.tx_mainPlay.text = (currentItem.snippet.title)

        /**
         * set the inner recyclerview of the actual songs
         */
        viewHolder.recyclerView?.layoutManager =
            LinearLayoutManager(viewHolder.recyclerView.context, RecyclerView.VERTICAL, false)
        var adapter =
            SongListRecyclerView(currentItem.playlistItems.items as ArrayList<ItemX>, context, this)
        viewHolder.recyclerView?.adapter = adapter

        // handle selected events
        if (position != this.pos) {
            viewHolder.recyclerView.visibility = View.GONE
        } else {
            viewHolder.recyclerView.visibility = View.VISIBLE
        }


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    override fun onItemSongClick(i: Int) {
        listener.onSongClick(i, pos)
    }

}