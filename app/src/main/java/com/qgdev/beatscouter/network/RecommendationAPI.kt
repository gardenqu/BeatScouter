package com.qgdev.beatscouter.network

import com.qgdev.beatscouter.model.SpotifyTrack
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


data class RecommendationRequest(
    val trackName: String,
    val trackArtist: String
)

interface RecommendationApi {
    @POST("recommend")
    fun getRecommendations(
        @Body request: RecommendationRequest
    ): Call<List<SpotifyTrack>>
}