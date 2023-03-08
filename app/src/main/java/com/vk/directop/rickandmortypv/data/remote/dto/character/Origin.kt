package com.vk.directop.rickandmortypv.data.remote.dto.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Origin(
    val name: String,
    val url: String
): Parcelable