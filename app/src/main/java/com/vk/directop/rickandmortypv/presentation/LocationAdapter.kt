package com.vk.directop.rickandmortypv.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.databinding.LocationItemBinding

class LocationAdapter(
    private val actionListener: OnLocationListener
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>(), View.OnClickListener {

    inner class LocationViewHolder(val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val locations: ArrayList<LocationDTO> = arrayListOf()

//    private val diffCallback = object : DiffUtil.ItemCallback<LocationDTO>() {
//        override fun areItemsTheSame(oldItem: LocationDTO, newItem: LocationDTO): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: LocationDTO, newItem: LocationDTO): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)
//    var locations: List<LocationDTO>
//        get() = differ.currentList
//        set(value) {
//            differ.submitList(value)
//        }

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

            cardView.setOnClickListener {
                actionListener.onLocationClick(locationRM)
            }
        }
    }

    override fun getItemCount(): Int = locations.size

    override fun onClick(view: View) {
        val locationDTO = view.tag as LocationDTO
        actionListener.onLocationClick(locationDTO)
    }

    fun submitUpdate(update: List<LocationDTO>) {
        val callback = LocationsDiffCallback(locations, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        locations.clear()
        locations.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class LocationsDiffCallback(
        private val oldLocations: List<LocationDTO>,
        private val newLocations: List<LocationDTO>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldLocations.size
        }

        override fun getNewListSize(): Int {
            return newLocations.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLocations[oldItemPosition].id == newLocations[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldLocations[oldItemPosition].url == newLocations[newItemPosition].url
        }
    }

    interface OnLocationListener {
        fun onLocationClick(location: LocationDTO)
    }
}