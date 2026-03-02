package com.example.eduadmission.ui.university

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentUniversityListBinding
import com.example.eduadmission.ui.adapter.UniversityAdapter
import com.example.eduadmission.viewmodel.EduViewModelFactory
import com.example.eduadmission.viewmodel.UniversityViewModel

class UniversityListFragment : Fragment(R.layout.fragment_university_list) {

    private val viewModel: UniversityViewModel by viewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUniversityListBinding.bind(view)

        val adapter = UniversityAdapter { university ->
            val bundle = Bundle().apply {
                putString("universityId", university.id)
            }
            findNavController().navigate(R.id.action_universityListFragment_to_courseListFragment, bundle)
        }

        binding.rvUniversities.layoutManager = LinearLayoutManager(context)
        binding.rvUniversities.adapter = adapter

        viewModel.universities.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.loadUniversities()
    }
}
