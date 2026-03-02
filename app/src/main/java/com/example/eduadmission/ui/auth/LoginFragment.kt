package com.example.eduadmission.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eduadmission.EduApp
import com.example.eduadmission.R
import com.example.eduadmission.databinding.FragmentLoginBinding
import com.example.eduadmission.viewmodel.AuthViewModel
import com.example.eduadmission.viewmodel.EduViewModelFactory
import androidx.core.content.edit

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: AuthViewModel by viewModels {
        EduViewModelFactory((requireActivity().application as EduApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, "User")
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnRegister.setOnClickListener {
             findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                sharedPref.edit {
                    putBoolean("is_logged_in", true)
                    putString("user_email", binding.etEmail.text.toString())
                }
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }
}
