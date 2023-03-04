package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Origin(
    val name: String,
    val url: String
): Parcelable