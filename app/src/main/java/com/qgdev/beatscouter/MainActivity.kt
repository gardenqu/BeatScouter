package com.qgdev.beatscouter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.qgdev.beatscouter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.searchButton.setOnClickListener {

            val trackName = binding.songNameTB.text.toString()
            val trackArtist = binding.artistNameInput.text.toString()

            if (trackName.isNotEmpty() && trackArtist.isNotEmpty()) {
                val intent = Intent(this, ListActivity2::class.java)
                intent.putExtra("trackName", trackName)
                intent.putExtra("trackArtist", trackArtist)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter both song name and artist", Toast.LENGTH_SHORT).show()
            }
        }
    }


}