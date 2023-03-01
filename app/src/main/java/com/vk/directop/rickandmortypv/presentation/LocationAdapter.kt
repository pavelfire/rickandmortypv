package com.vk.directop.rickandmortypv.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character.CharacterRM
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.location.LocationRM
import com.vk.directop.rickandmortypv.databinding.CharacterItemBinding
import com.vk.directop.rickandmortypv.databinding.LocationItemBinding

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<LocationRM>() {
        override fun areItemsTheSame(oldItem: LocationRM, newItem: LocationRM): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationRM, newItem: LocationRM): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var locations: List<LocationRM>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.binding.apply {
            val locationRM = locations[position]
            tvName.text = locationRM.name
            tvType.text = locationRM.type
            tvDimension.text = locationRM.dimension
        }
    }

    override fun getItemCount(): Int = locations.size

}