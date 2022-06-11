package com.nicole.middlerterm

import android.os.Parcelable
import com.google.rpc.context.AttributeContext
import kotlinx.parcelize.Parcelize


@Parcelize
data class PublishDetail(
    val author: Author,
    val title: String,
    val content:String,
    val createdTime: Long,
    val ID: String,
    val category: String
):Parcelable
