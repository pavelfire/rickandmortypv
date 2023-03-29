package com.vk.directop.rickandmortypv.domain.common

sealed class Resultss<out R> {

    data class Success<out T>(val data: T) : Resultss<T>()
    data class Error(val exception: Exception) : Resultss<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
