package com.example.eduadmission.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduadmission.R
import com.example.eduadmission.data.model.ApplicationUiModel
import com.example.eduadmission.databinding.ItemApplicationBinding

class ApplicationAdapter(
    private val onStatusChange: (Long, String) -> Unit
) : RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>() {

    private var applications = listOf<ApplicationUiModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ApplicationUiModel>) {
        applications = list
        notifyDataSetChanged()
    }

    fun getItem(position: Int): ApplicationUiModel = applications[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val binding =
            ItemApplicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        holder.bind(applications[position])
    }

    override fun getItemCount() = applications.size

    inner class ApplicationViewHolder(private val binding: ItemApplicationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(app: ApplicationUiModel) {
            val context = binding.root.context
            binding.tvAppCourse.text = app.courseName
            binding.tvAppUni.text = app.universityName
            binding.tvAppUniId.text = context.getString(R.string.uni_id_format, app.universityId)
            binding.tvApplicantName.text =
                context.getString(R.string.applicant_name_format, app.fullname)
            binding.tvApplicantEmail.text =
                context.getString(R.string.applicant_email_format, app.userEmail)

            binding.tvAppStatus.text = context.getString(R.string.status_format, app.status)

            // Set Color based on status
            val statusColor = when (app.status) {
                context.getString(R.string.submitted) -> R.color.status_submitted
                context.getString(R.string.under_review) -> R.color.status_review
                context.getString(R.string.accepted) -> R.color.status_accepted
                context.getString(R.string.rejected) -> R.color.status_rejected
                else -> R.color.status_submitted
            }
            binding.tvAppStatus.setTextColor(
                androidx.core.content.ContextCompat.getColor(
                    context,
                    statusColor
                )
            )

            // Long Press to change status
            binding.root.setOnLongClickListener {
                val nextStatus = when (app.status) {
                    context.getString(R.string.submitted) -> context.getString(R.string.under_review)
                    context.getString(R.string.under_review) -> context.getString(R.string.accepted)
                    context.getString(R.string.accepted) -> context.getString(R.string.rejected)
                    else -> context.getString(R.string.submitted)
                }
                onStatusChange(app.applicationId, nextStatus)
                true // Consume the long click
            }
        }
    }
}
