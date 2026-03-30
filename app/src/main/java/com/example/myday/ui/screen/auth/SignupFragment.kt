package com.example.myday.ui.screen.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myday.R
import androidx.navigation.fragment.findNavController
import com.example.myday.DashboardActivity
import com.example.myday.data.remote.request.signup.SignUpRequest
import com.example.myday.data.remote.request.signup.User
import com.example.myday.databinding.SignUpFragmentBinding
import com.example.myday.ui.viewmodel.MyDayViewModel
import com.example.myday.utils.NetworkResult
import com.example.myday.utils.TokenManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue


@AndroidEntryPoint
class SignupFragment: Fragment() {

    //private val myViewModel by viewModels<MyDayViewModel>()
    private lateinit var binding: SignUpFragmentBinding

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            signUp(username, email, password)
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment2)
        }
    }

    private fun signUp(username: String, email: String, password: String) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {

            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        val uid = user?.uid

                        uid?.let {
                            tokenManager.saveToken(it)
                        }

                        Log.d("TOKEN", tokenManager.getToken().toString())

                        Toast.makeText(requireContext(), "Signup Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(requireContext(), DashboardActivity::class.java))
                    }
                    else {
                        Toast.makeText(requireContext(), "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
