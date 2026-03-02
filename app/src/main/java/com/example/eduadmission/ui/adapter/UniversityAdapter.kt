package com.example.eduadmission.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eduadmission.data.model.University
import com.example.eduadmission.databinding.ItemUniversityBinding

class UniversityAdapter(
    private val onClick: (University) -> Unit
) : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    private var universities = listOf<University>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<University>) {
        universities = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(universities[position])
    }

    override fun getItemCount() = universities.size

    inner class UniversityViewHolder(private val binding: ItemUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(university: University) {
            binding.tvUniName.text = university.name
            binding.tvUniLocation.text = university.location
            binding.tvRating.text = "${university.rating} ★"
            
            if (university.imageUrl.isNotEmpty()) {
                binding.ivUniLogo.load(university.imageUrl) {
                    crossfade(true)
                    placeholder(com.example.eduadmission.R.mipmap.ic_launcher)
                    error(com.example.eduadmission.R.mipmap.ic_launcher)
                    transformations(coil.transform.CircleCropTransformation())
                }
            } else {
                binding.ivUniLogo.setImageResource(com.example.eduadmission.R.mipmap.ic_launcher)
            }
            
            binding.root.setOnClickListener { onClick(university) }
        }
    }
}
