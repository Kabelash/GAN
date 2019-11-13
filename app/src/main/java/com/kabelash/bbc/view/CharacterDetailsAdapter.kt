package com.kabelash.bbc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kabelash.bbc.R
import com.kabelash.bbc.model.BreakingBadCharacters
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_details_view.view.*

/*
* Created by Kabelash on 13.11.2019
* */

class CharacterDetailsAdapter(val feed: Array<BreakingBadCharacters>): RecyclerView.Adapter<CharacterDetailsViewHolder>(){

    override fun getItemCount(): Int {
        return feed.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowCell = layoutInflater.inflate(R.layout.character_details_view, parent, false)
        return CharacterDetailsViewHolder(rowCell)
    }

    override fun onBindViewHolder(holder: CharacterDetailsViewHolder, position: Int) {
        val character = feed[position]

        val fd1 = character.name
        val fd2 = character.img
        holder.view.detailName.text = fd1.toString() //Setting character name

        //Used Picasso to set image url
        Picasso.get()
            .load(fd2)
            .resize(60, 60)
            .centerCrop()
            .into(holder.view.charImage)

    }

}

class CharacterDetailsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    

}