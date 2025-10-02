package com.qgdev.beatscouter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qgdev.beatscouter.adapter.TrackAdapter
import com.qgdev.beatscouter.model.SpotifyTrack
import com.qgdev.beatscouter.network.RecommendationApi
import com.qgdev.beatscouter.network.RecommendationRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class ListActivity2 : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    val gson = Gson()
    val listType: Type = object : TypeToken<List<SpotifyTrack>>() {}.type
    lateinit var trackAdapter: TrackAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_list)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val errorMessage = findViewById<TextView>(R.id.errorMessage)
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // optional: closes current activity
        }


        val trackName = intent.getStringExtra("trackName") ?: ""
        val trackArtist = intent.getStringExtra("trackArtist") ?: ""


        recycler = findViewById(R.id.recyclerView)
        recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://spotifyrecommondationapi.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecommendationApi::class.java)
        val request = RecommendationRequest(trackName = trackName, trackArtist = trackArtist)


        api.getRecommendations(request).enqueue(object : Callback<List<SpotifyTrack>> {
            override fun onResponse(
                call: Call<List<SpotifyTrack>>,
                response: Response<List<SpotifyTrack>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val tracks = response.body()!!
                    if (tracks.isNotEmpty()) {
                        trackAdapter = TrackAdapter(tracks.toMutableList(), this@ListActivity2)
                        recycler.adapter = trackAdapter
                        errorMessage.visibility = View.GONE
                    } else {
                        recycler.adapter = null
                        errorMessage.visibility = View.VISIBLE
                    }
                } else {
                    recycler.adapter = null
                    errorMessage.visibility = View.VISIBLE
                    backButton.visibility=View.VISIBLE
                    Log.e("API ERROR", "API error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SpotifyTrack>>, t: Throwable) {
                Log.e("API ERROR:", "API failed", t)
            }
        })



    }
}