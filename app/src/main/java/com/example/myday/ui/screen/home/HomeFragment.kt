package com.example.myday.ui.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myday.R
import com.example.myday.adapter.ArticleAdapter
import com.example.myday.databinding.FragmentHomeBinding
import com.example.myday.ui.viewmodel.MyDayUserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val myViewModel by viewModels<MyDayUserViewModel>()
    private lateinit var binding: FragmentHomeBinding

    private lateinit var myFeedAdapter : ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())


        myViewModel.getPost()
        myViewModel.getPostData.observe(viewLifecycleOwner){
            val list = it.data?.articles
            myFeedAdapter = ArticleAdapter(list)
            binding.rv.adapter = myFeedAdapter
        }
    }
    
}