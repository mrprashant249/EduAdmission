package com.example.eduadmission.ui.university

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentCourseDetailBinding
import com.example.eduadmission.viewmodel.EduViewModelFactory
import com.example.eduadmission.viewmodel.UniversityViewModel

class CourseDetailFragment : Fragment(R.layout.fragment_course_detail) {

    private val viewModel: UniversityViewModel by viewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCourseDetailBinding.bind(view)
        
        val courseId = arguments?.getString("courseId") ?: return

        viewModel.selectCourse(courseId)
        
        viewModel.selectedCourse.observe(viewLifecycleOwner) { course ->
             binding.tvDetailTitle.text = course.title
             binding.tvDetailDuration.text = course.duration
             binding.tvDetailFees.text = course.fees
             binding.tvDetailDescription.text = course.description
             
             binding.btnApplyNow.setOnClickListener {
                 val bundle = Bundle().apply {
                     putString("courseId", course.id)
                     putString("universityId", course.universityId)
                     putString("courseName", course.title) 
                     // Pass more info if needed
                 }
                 findNavController().navigate(R.id.action_courseDetailFragment_to_applicationFormFragment, bundle)
             }
        }
    }
}
