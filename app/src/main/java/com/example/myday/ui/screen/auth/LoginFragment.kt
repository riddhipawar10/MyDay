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
import com.example.myday.DashboardActivity
import com.example.myday.R
import com.example.myday.databinding.LoginFragmentBinding
import com.example.myday.utils.TokenManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment){
    //private val myViewModel by viewModels<MyDayViewModel>()
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
        //handleLogin()
    }
    private fun login(email: String, password: String) {
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast.makeText(requireContext(), "Please fill every fields", Toast.LENGTH_SHORT).show()
            return
        }else{
            val auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val uid = FirebaseAuth.getInstance().currentUser?.uid

                        uid?.let {
                            tokenManager.saveToken(it)
                        }

                        Log.d("TOKEN", tokenManager.getToken().toString())

                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(requireContext(), DashboardActivity::class.java))
                    } else {
                        Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}