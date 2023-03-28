package com.vk.directop.rickandmortypv.data.remote.dto.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val name: String,
    val url: String
) : Parcelable