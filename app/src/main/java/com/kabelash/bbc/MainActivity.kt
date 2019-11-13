package com.kabelash.bbc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.kabelash.bbc.model.BreakingBadCharacters
import com.kabelash.bbc.view.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

/*
* Created by Kabelash on 13.11.2019
* */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this);

        fetchJson()

    }

    // Fetch JSON
    fun fetchJson() {
        val url = "https://breakingbadapi.com/api/characters"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body) // To get Json on Logcat

                val gson = GsonBuilder().create()

                //got Json object as array
                val feed = gson.fromJson(body, Array<BreakingBadCharacters>::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(feed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Request Failed" + e)
            }
        })
    }
}