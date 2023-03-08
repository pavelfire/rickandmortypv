package com.vk.directop.rickandmortypv.presentation

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.databinding.CharacterItemBinding

class CharacterAdapter(
    private val actionListener: OnCharacterListener
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), View.OnClickListener {

    inner class CharacterViewHolder(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<CharacterDTO>() {
        override fun areItemsTheSame(oldItem: CharacterDTO, newItem: CharacterDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterDTO, newItem: CharacterDTO): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var characters: List<CharacterDTO>
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
            cardView.setOnClickListener {
                actionListener.onCharacterClick(characterRM)
            }
        }
    }

    override fun getItemCount(): Int = characters.size

    override fun onClick(view: View) {
        val characterDTO = view.tag as CharacterDTO
        Log.d("TAG", "Clicked onClick in adapter ${characterDTO.name}")
        actionListener.onCharacterClick(characterDTO)
    }

    interface OnCharacterListener {
        fun onCharacterClick(character: CharacterDTO)
    }

}