package com.example.myday.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myday.R
import com.example.myday.data.remote.response.Article
import com.example.myday.data.remote.response.Author
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : Fragment() {

    //private var _binding: FragmentCreatePostBinding? = null
    //private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    private lateinit var titleEt: EditText
    private lateinit var descriptionEt: EditText
    private lateinit var bodyEt: EditText
    private lateinit var btnPost: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_post, container, false)

        titleEt = view.findViewById(R.id.titleEt)
        descriptionEt = view.findViewById(R.id.descriptionEt)
        bodyEt = view.findViewById(R.id.bodyEt)
        btnPost = view.findViewById(R.id.btnPost)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPost.setOnClickListener {
            val title = titleEt.text.toString().trim()
            val description = descriptionEt.text.toString().trim()
            val body = bodyEt.text.toString().trim()
            val userId = "testUser" // Replace with real userId later

            if (title.isEmpty() || description.isEmpty() || body.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val article = Article(
                title = title,
                description = description,
                body = body,
                userId = userId,
                favorited = false,
                favoritesCount = 0,
                slug = "",
                tagList = emptyList(),
                author = Author(bio = "", following = false, image = "", username = userId)
            )

            db.collection("articles")
                .add(article)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Article posted!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}