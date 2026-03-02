package com.example.eduadmission.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduadmission.data.model.Course
import com.example.eduadmission.databinding.ItemCourseBinding

class CourseAdapter(
    private val onClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var courses = listOf<Course>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Course>) {
        courses = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount() = courses.size

    inner class CourseViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.tvCourseTitle.text = course.title
            binding.tvDuration.text = course.duration
            binding.tvFees.text = course.fees
            binding.root.setOnClickListener { onClick(course) }
        }
    }
}
