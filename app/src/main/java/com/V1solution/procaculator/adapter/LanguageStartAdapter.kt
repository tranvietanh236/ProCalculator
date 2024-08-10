package com.V1solution.procaculator.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.V1solution.procaculator.model.LanguageModel
import com.bumptech.glide.Glide
import com.travietnanh.procaculator.R
import com.travietnanh.procaculator.databinding.ItemLanguageBinding

class LanguageStartAdapter(
    private var list: List<LanguageModel>,
    private val context: Context,
    private val listener: OnClickListener
) :
    RecyclerView.Adapter<LanguageStartAdapter.LanguageStartViewHolder>() {

    class LanguageStartViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.rootLanguage)

    fun updateData(newList: List<LanguageModel>) {
        list = newList
        for (language in list) {
            Log.d("LanguageAdapter", "${language.code} , ${language.active}")
        }
        notifyDataSetChanged()

    }

    fun setSelectedLanguage(selectedLanguage: LanguageModel) {
        for (data in list) {
            data.active = data == selectedLanguage
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageStartViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageStartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LanguageStartViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            Glide.with(context).load(data.image).apply {
                into(holder.binding.ivLanguage)
            }
            holder.binding.tvLanguage.text = data.languageName

            if (data.active) {
                holder.binding.rootLanguage.setBackgroundResource(R.drawable.bg_language_on)
                holder.binding.tvLanguage.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                val typeface = ResourcesCompat.getFont(context, R.font.opensans_bold)
                holder.binding.tvLanguage.typeface = typeface
            } else {
                holder.binding.rootLanguage.setBackgroundResource(R.drawable.bg_language_off)
                holder.binding.tvLanguage.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )
                val typeface = ResourcesCompat.getFont(context, R.font.opensans_regular)
                holder.binding.tvLanguage.typeface = typeface
            }

            setOnClickListener {
                listener.onClick(data)
            }
        }
    }

    interface OnClickListener {
        fun onClick(languageModel: LanguageModel)
    }
}