package com.qgdev.beatscouter.model

import com.google.gson.annotations.SerializedName

data class SpotifyTrack(
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("track_name")
    val trackName: String,
    @SerializedName("artwork_url")
    val artworkUrl: String,
    @SerializedName("similarity_score")
    val similarityScore: Double,
    @SerializedName("track_url")
    val trackUrl: String
)