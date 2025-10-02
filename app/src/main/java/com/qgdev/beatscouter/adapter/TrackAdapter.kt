package com.qgdev.beatscouter.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qgdev.beatscouter.R
import com.qgdev.beatscouter.model.SpotifyTrack
import androidx.core.net.toUri

class TrackAdapter(
    private val trackList: MutableList<SpotifyTrack>,
    private val context: Context
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackTitle: TextView = itemView.findViewById(R.id.songNameLV)
        val trackArtist: TextView = itemView.findViewById(R.id.artistTextLV)
        val trackImage: ImageView = itemView.findViewById(R.id.imageView)
        val trackSimScore: TextView = itemView.findViewById(R.id.compatibilityScoreLV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = trackList[position]
        holder.trackTitle.text = track.trackName
        holder.trackArtist.text = track.artistName
        val score = (track.similarityScore * 100).toInt()
        holder.trackSimScore.text = context.getString(R.string.compatiblity, score)

        Glide.with(context)
            .load(track.artworkUrl)
            .into(holder.trackImage)

        holder.trackImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = track.trackUrl.toUri()
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = trackList.size

}
