package com.kabelash.bbc.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kabelash.bbc.CharacterDetails
import com.kabelash.bbc.R
import com.kabelash.bbc.model.BreakingBadCharacters
import kotlinx.android.synthetic.main.repo_row.view.*
import com.squareup.picasso.Picasso
import android.graphics.Movie
import android.R



/*
* Created by Kabelash on 30.09.2019
* */

class MainAdapter(val feed: Array<BreakingBadCharacters>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return feed.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowCell = layoutInflater.inflate(R.layout.repo_row, parent, false)
        return CustomViewHolder(rowCell)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val character = feed[position]

        val fd1 = character.name
        val fd2 = character.img
        holder.view.characterName.text = fd1.toString() //Setting character name

        //Used Picasso to set image url
        Picasso.get()
            .load(fd2)
            .resize(60, 60)
            .centerCrop()
            .into(holder.view.characterImg)

        holder.character = character

    }

}

class CustomViewHolder(val view: View, var character: BreakingBadCharacters? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val CHAR_ID = "CHAR ID"
        val CRT = "CHAR_NAME"
        val CHAR_IMG = "CHAR_IMG"
        val CHAR_OCCUPATION = "CHAR_OCCUPATION"
        val CHAR_STATUS = "CHAR_STATUS"
        val CHAR_NICKNAME = "CHAR_NICKNAME"
        val CHAR_APPEARANCE = "CHAR_APPEARANCE"
    }


    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CharacterDetails::class.java)

            intent.putExtra(CRT, character?.name)
            intent.putExtra(CHAR_ID, character?.char_id)
            intent.putExtra(CHAR_ID, character?.img)
            intent.putExtra(CHAR_ID, character?.occupation)
            intent.putExtra(CHAR_ID, character?.nickname)
            intent.putExtra(CHAR_ID, character?.char_id)
            intent.putExtra(CHAR_ID, character?.char_id)


            view.context.startActivity(intent)
        }
    }

}
