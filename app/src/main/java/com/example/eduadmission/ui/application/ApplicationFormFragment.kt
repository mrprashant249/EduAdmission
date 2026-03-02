package com.example.eduadmission.ui.application

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.data.local.ApplicationEntity
import com.example.eduadmission.databinding.FragmentApplicationFormBinding
import com.example.eduadmission.viewmodel.ApplicationViewModel
import com.example.eduadmission.viewmodel.EduViewModelFactory

class ApplicationFormFragment : Fragment(R.layout.fragment_application_form) {

    private val viewModel: ApplicationViewModel by activityViewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    private var uploadedUri: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentApplicationFormBinding.bind(view)

        val courseId = arguments?.getString("courseId") ?: return
        val universityId = arguments?.getString("universityId") // Not used in Entity but good to have context
        val courseName = arguments?.getString("courseName") ?: "Course" // Pass this for entity

        binding.btnUpload.setOnClickListener {
            // Simulate upload
            uploadedUri = "content://simulated/document.pdf"
            binding.tvUploadStatus.text = "document.pdf uploaded successfully"
        }

        binding.btnSubmitApplication.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val qualification = binding.etQualification.text.toString()

            if (fullName.isEmpty() || qualification.isEmpty()) {
                Toast.makeText(context, "Please complete all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (uploadedUri == null) {
                Toast.makeText(context, "Please upload documents", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val userEmail = sharedPref.getString("user_email", null)

            if (userEmail != null) {
                val app = ApplicationEntity(
                    userId = userEmail,
                    userEmail = userEmail,
                    fullname = fullName,
                    universityId = universityId ?: "Unknown",
                    courseId = courseId,
                    universityName = "", // Not stored/used, resolved dynamically
                    courseName = "",     // Not stored/used, resolved dynamically
                    status = "Submitted",
                    submittedAt = System.currentTimeMillis(),
                    documentUri = uploadedUri
                )
                viewModel.submitApplication(app)
                Toast.makeText(context, "Application Submitted", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_applicationFormFragment_to_statusFragment)
            } else {
                Toast.makeText(context, "Error: User not logged in", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
