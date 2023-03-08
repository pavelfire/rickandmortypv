package com.vk.directop.rickandmortypv

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterItem(
val id: Int,
val name: String
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = CharacterItem(id = 3, name = "false")
    }
}
