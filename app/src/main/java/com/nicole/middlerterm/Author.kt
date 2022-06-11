package com.nicole.middlerterm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Author(
    val email: String,
    val id: String,
    val name: String,
): Parcelable