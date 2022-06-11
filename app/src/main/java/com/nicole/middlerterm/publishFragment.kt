package com.nicole.middlerterm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nicole.middlerterm.databinding.FragmentPublishBinding


class PublishFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPublishBinding.inflate(layoutInflater)

        val db = Firebase.firestore

        val viewModel = MainViewModel()

//        val demoDialogFragment = PublishFragment()
//        demoDialogFragment.show(supportFragmentManager, "")

// Add a new document with a generated ID
        binding.button.setOnClickListener {

            val title = binding.editTextTitle.text.toString()
            val category = binding.editTextCategory.text.toString()
            val content = binding.editTextContent.text.toString()
            val time = System.currentTimeMillis()
            Log.d("Nicole", "我要看內容$title $category $content $time")
            val author = hashMapOf(
                "email" to "A4207486@gmail.com",
                "id" to "A4207486",
                "name" to "Nicole"
            )
            val user = hashMapOf(
                "author" to author,
                "category" to category,
                "content" to content,
                "createdTime" to time,
                "title" to title
            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("Nicole這次新增", "DocumentSnapshot added with ID: ${documentReference.id}")
                    val id = documentReference.id

                    val updateMap: MutableMap<String, Any> = HashMap()
                    updateMap["id"] = id

                    db.collection("users")
                        .document(documentReference.id)
                        .update(updateMap)
                    viewModel.getDetailData()

                }
                .addOnFailureListener { e ->
                    Log.w("Nicole這次新增", "Error adding document", e)
                }
            //把dialog頁面關掉
            onDestroyView()
        }

        //在run後, 會先跑一次firebase全部的資料
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("全部的資料", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("全部的資料", "Error getting documents.", exception)
            }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}