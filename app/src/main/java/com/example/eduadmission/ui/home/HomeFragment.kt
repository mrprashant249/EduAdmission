package com.example.eduadmission.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentHomeBinding
import com.example.eduadmission.ui.adapter.UniversityAdapter
import com.example.eduadmission.viewmodel.EduViewModelFactory
import com.example.eduadmission.viewmodel.HomeViewModel
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.core.content.edit

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val adapter = UniversityAdapter { university ->
            val bundle = Bundle().apply {
                putString("universityId", university.id)
            }
            findNavController().navigate(R.id.action_homeFragment_to_courseListFragment, bundle)
        }
        
        binding.rvFeatured.layoutManager = LinearLayoutManager(context)
        binding.rvFeatured.adapter = adapter

        viewModel.featuredUniversities.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        
        viewModel.loadFeatured()

        binding.cardUniversities.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_universityListFragment)
        }

        binding.cardApplications.setOnClickListener {
             findNavController().navigate(R.id.action_homeFragment_to_statusFragment)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            @SuppressLint("UseKtx")
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_logout -> {
                        // Clear preferences
                        val sharedPref = requireActivity().getPreferences(android.content.Context.MODE_PRIVATE)
                        sharedPref.edit {
                            clear()
                        }
                        
                        // Navigate to Login and clear back stack
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
