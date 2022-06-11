package com.nicole.middlerterm

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel() : ViewModel() {
    //     PublishDetail has product data from arguments
    private val _product = MutableLiveData<List<PublishDetail>>()

    // PublishDetail是整包所需的資料
    val product: LiveData<List<PublishDetail>>
        get() = _product

    val db = Firebase.firestore


    fun getDetailData() {
//        db.collection("users")
//            .get()
//            .addOnCompleteListener { allData ->
//                if (allData.isSuccessful) {
//                    for (document in allData.result) {
//                        Log.d(TAG, document.id + " => " + document.data)
//                        val allDocument = document.data as MutableMap<String, Any>
//                        Log.d("Nicole", "getDocument =${allDocument} ")
//                        val getAuthor = allDocument["author"] as MutableMap<*,*>
//                        Log.d("Nicole", "getAuthor =${getAuthor} ")
//                        val author = Author(
//                            getAuthor["email"].toString(),
//                            getAuthor["id"].toString(),
//                            getAuthor["name"].toString()
//                        )
//                        val article = PublishDetail(
//                            author,
//                            allDocument["title"].toString(),
//                            allDocument["author"].toString(),
//                            allDocument["time"].toString().toLong(),
//                            allDocument["category"].toString(),
//                            allDocument["content"].toString()
//                        )
//                        Log.d("Nicole", "article=$article")
//                        _product.value = listOf(article)
//
//                    }
//                } else {
//                    Log.w(TAG, "Error getting documents.", allData.exception)
//                }
//            }
    }
}
//    val adapter = HomeAdapter()
//    binding.recyclerView.adapter = adapter
//
////        getAllData()
//
//    val allData = mutableListOf<Article>()
//
//    val articles = FirebaseFirestore.getInstance().collection("articles")
//    articles.get()
//    .addOnCompleteListener { task ->
//        if (task.isSuccessful) {
//            for (document in task.result) {
//                Log.d(TAG, document.id + " => " + document.data)
//                val getDocument = document.data as MutableMap<String, Any>
//                Log.d("Mark", "getDocument =${getDocument} ")
//                val getAuthor = getDocument["author"] as MutableMap<,>
//                Log.d("Mark", "getAuthor =${getAuthor} ")
//                val author = Author(
//                    getAuthor["email"].toString(),
//                    getAuthor["id"].toString(),
//                    getAuthor["name"].toString()
//                )
//                val article = Article(
//                    author,
//                    getDocument["category"].toString(),
//                    getDocument["content"].toString(),
//                    getDocument["createdTime"].toString().toLong(),
//                    getDocument["id"].toString(),
//                    getDocument["title"].toString()
//                )
//                Log.d("Mark","article=$article")
//                allData.add(article)
//
//            }
//            Log.d("Mark","allData1=$allData")
//            adapter.submitList(allData)
//        } else {
//            Log.w(TAG, "Error getting documents.", task.exception)
//        }
//    }