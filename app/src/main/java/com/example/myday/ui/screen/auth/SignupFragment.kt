package com.example.myday.ui.screen.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue


@AndroidEntryPoint
class SignupFragment: Fragment() {

    private val myViewModel by viewModels<MyDayViewModel>()
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

        if (tokenManager.getToken()!=null){
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
        }else{
            binding.btnSignUp.setOnClickListener {
                val username = binding.txtUsername.text.toString()
                val email = binding.txtEmail.text.toString()
                val password = binding.txtPassword.text.toString()
                signUp(username,email,password)
            }
            handleSigUp()
            binding.btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment2)
            }
        }
    }

    private fun signUp(username: String, email: String, password: String) {
        if(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password)&& TextUtils.isEmpty(username)){
            Toast.makeText(requireContext(), "Please fill every fields", Toast.LENGTH_SHORT).show()
        }else{
            myViewModel.signup(SignUpRequest(User(username,email,password)))
        }
    }

    private fun handleSigUp(){
        myViewModel.signupResponseLiveData.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Success ->{
                    Toast.makeText(requireContext(), "${it.data}", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Failed -> {
                    Toast.makeText(requireContext(), "Something went wrong ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
