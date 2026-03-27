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
import com.example.myday.DashboardActivity
import com.example.myday.R
import com.example.myday.data.remote.request.login.LoginRequest
import com.example.myday.data.remote.request.login.User
import com.example.myday.databinding.LoginFragmentBinding
import com.example.myday.ui.viewmodel.MyDayViewModel
import com.example.myday.utils.NetworkResult
import com.example.myday.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment){
    private val myViewModel by viewModels<MyDayViewModel>()
    private lateinit var binding: LoginFragmentBinding
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            login(email,password)
        }
        handleLogin()
    }
    private fun login(email: String, password: String) {
        if(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password)){
            Toast.makeText(requireContext(), "Please fill every fields", Toast.LENGTH_SHORT).show()
        }else{
            myViewModel.login(LoginRequest(User(email,password)))
        }
    }

    private fun handleLogin(){
        myViewModel.loginResponseLiveData.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Success ->{
                    it.data?.user?.token?.let { token -> tokenManager.saveToken((token)) }
                    startActivity(Intent(this.context, DashboardActivity::class.java))
                    Toast.makeText(requireContext(), it.data?.user?.username, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Failed -> {
                    Toast.makeText(requireContext(), "Something went wrong ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}