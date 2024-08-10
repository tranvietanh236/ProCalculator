package com.V1solution.procaculator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.V1solution.procaculator.model.IntroModel
import com.bumptech.glide.Glide
import com.travietnanh.procaculator.databinding.ItemIntroBinding

class IntroAdapter (val context: Context) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>(){
    var list: MutableList<IntroModel> = mutableListOf()
    inner class IntroViewHolder(val binding : ItemIntroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val binding = ItemIntroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            Glide.with(context).load(data.imageIntro).apply {
                into(holder.binding.imgIntro)
            }
        }
    }
}