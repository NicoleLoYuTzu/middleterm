package com.nicole.middlerterm

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nicole.middlerterm.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.buttonCoupon.setOnClickListener {
            val demoDialogFragment = PublishFragment()
            demoDialogFragment.show(supportFragmentManager, "")
        }

        val viewModel = MainViewModel()

        val db = Firebase.firestore
        val adapter = MainAdapter()

        val allData = mutableListOf<PublishDetail>()


        val articles = FirebaseFirestore.getInstance()
            .collection("users")
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "category" to "Beauty"
        )
        document.set(data)



        db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        Log.d(TAG, document.id + " => " + document.data)
                        val getDocument = document.data as MutableMap<String, Any>
                        Log.d("Nicole", "getDocument =${getDocument} ")
                        val getAuthor = getDocument["author"] as MutableMap<String, Any>
                        Log.d("Nicole", "getAuthor =${getAuthor} ")
                        val author = Author(
                            getAuthor["email"].toString(),
                            getAuthor["id"].toString(),
                            getAuthor["name"].toString()
                        )
                        val article = PublishDetail(
                            author,
                            getDocument["title"].toString(),
                            getDocument["author"].toString(),
                            getDocument["createdTime"].toString().toLong(),
                            getDocument["category"].toString(),
                            getDocument["content"].toString()
                        )
                        Log.d("Nicole", "article=$article")
                        allData.add(article)

                    }
                    Log.d("Nicole", "allData1=$allData")
                    adapter.submitList(allData)
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }

        binding.recyclerView.adapter = adapter

        setContentView(binding.root)
    }

}