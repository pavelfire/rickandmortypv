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
import com.vk.directop.rickandmortypv.databinding.CharacterItemBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<CharacterRM>() {
        override fun areItemsTheSame(oldItem: CharacterRM, newItem: CharacterRM): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterRM, newItem: CharacterRM): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var characters: List<CharacterRM>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            val characterRM = characters[position]
            tvName.text = characterRM.name
            tvSpecies.text = characterRM.species
            tvStatus.text = characterRM.status
            tvGender.text = characterRM.gender
            if (characterRM.gender == "Male"){
                tvGender.setTextColor(Color.BLUE)
            }else{
                tvGender.setTextColor(Color.RED)
            }
            if (characterRM.image.isNotBlank()) {
                Glide.with(image.context)
                    .load(characterRM.image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_characters)
                    .error(R.drawable.ic_error_load)
                    .into(image)
            } else {
                image.setImageResource(R.drawable.ic_characters)
            }
        }
    }

    override fun getItemCount(): Int = characters.size

}