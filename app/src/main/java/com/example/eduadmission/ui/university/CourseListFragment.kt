package com.example.eduadmission.ui.university

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentCourseListBinding
import com.example.eduadmission.ui.adapter.CourseAdapter
import com.example.eduadmission.viewmodel.EduViewModelFactory
import com.example.eduadmission.viewmodel.UniversityViewModel

class CourseListFragment : Fragment(R.layout.fragment_course_list) {

    private val viewModel: UniversityViewModel by viewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCourseListBinding.bind(view)
        
        val universityId = arguments?.getString("universityId") ?: return

        val adapter = CourseAdapter { course ->
            val bundle = Bundle().apply {
                putString("courseId", course.id)
                putString("universityId", course.universityId)
            }
            findNavController().navigate(R.id.action_courseListFragment_to_courseDetailFragment, bundle)
        }

        binding.rvCourses.layoutManager = LinearLayoutManager(context)
        binding.rvCourses.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        
        viewModel.selectedUniversity.observe(viewLifecycleOwner) {
            binding.tvMainCourseTitle.text = it?.name ?: getString(R.string.course_list)
        }

        viewModel.loadCourses(universityId)
        viewModel.loadUniversity(universityId)
    }
}
