package com.example.myday.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myday.R
import com.example.myday.adapter.ArticleAdapter
import com.example.myday.data.remote.response.Article
import com.example.myday.data.remote.response.Author
import com.example.myday.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var myFeedAdapter: ArticleAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        fetchArticlesFromFirestore()

        binding.floatingActionButton.setOnClickListener {
            // Navigate to CreatePostFragment
            binding.floatingActionButton.setOnClickListener {
                view.findNavController().navigate(R.id.action_homeFragment_to_createPostFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchArticlesFromFirestore() // refresh when returning
    }

    private fun fetchArticlesFromFirestore() {
        db.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                val articlesList = result.map { doc ->
                    Article(
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        body = doc.getString("body") ?: "",
                        userId = doc.getString("userId") ?: "",
//                        createdAt = doc.getLong("createdAt") ?: 0L,
//                        updatedAt = doc.getLong("updatedAt") ?: 0L,
                        favorited = doc.getBoolean("favorited") ?: false,
                        favoritesCount = doc.getLong("favoritesCount")?.toInt() ?: 0,
                        slug = doc.getString("slug") ?: "",
                        tagList = doc.get("tagList") as? List<String> ?: emptyList(),
                        author = Author() // empty author
                    )
                }

                myFeedAdapter = ArticleAdapter(articlesList)
                binding.rv.adapter = myFeedAdapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error fetching articles: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}