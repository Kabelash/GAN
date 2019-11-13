package com.kabelash.bbc

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.kabelash.bbc.model.BreakingBadCharacters
import com.kabelash.bbc.view.CharacterDetailsAdapter
import com.kabelash.bbc.view.CustomViewHolder
import com.kabelash.bbc.view.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_details_view.*
import okhttp3.*
import java.io.IOException

class CharacterDetails  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details_view)

        val cName = intent.getStringExtra(CustomViewHolder.CRT)
        supportActionBar?.title = cName

        recyclerView_main.layoutManager = LinearLayoutManager(this);
        fetchJson2()
    }

    // Fetch JSON
    fun fetchJson2() {
        val cID = intent.getStringExtra(CustomViewHolder.CHAR_ID)
        val characterURL = "https://breakingbadapi.com/api/characters/$cID"

        val request = Request.Builder().url(characterURL).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body) // To get Json on Logcat

                val gson = GsonBuilder().create()

                //got Json object as array
                val cfeed = gson.fromJson(body, Array<BreakingBadCharacters>::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = CharacterDetailsAdapter(cfeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Request Failed" + e)
            }
        })
    }
}
