package com.vk.directop.rickandmortypv.contract

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.vk.directop.rickandmortypv.CharacterItem

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator{
    return requireActivity() as Navigator
}
interface Navigator {

    fun showSplashScreen()

    fun showCharactersScreen()

    fun showCharactersScreenDetail(character: CharacterItem)

    fun showLocationsScreen()

    fun showEpisodesScreen()

    fun goBack()

    fun <T: Parcelable> publishResult(result: T)

    fun <T : Parcelable>  listenResult(clazz: Class<T>, owner: LifecycleOwner, listener: ResultListener<T>)

}