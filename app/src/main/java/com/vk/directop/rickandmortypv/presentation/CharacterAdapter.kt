package com.vk.directop.rickandmortypv.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val characters: ArrayList<CharacterDTO> = arrayListOf()

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
            if (characterRM.gender == "Male") {
                tvGender.setTextColor(Color.BLUE)
            } else {
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
        actionListener.onCharacterClick(characterDTO)
    }

    fun submitUpdate(update: List<CharacterDTO>) {
        val callback = CharactersDiffCallback(characters, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        characters.clear()
        characters.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class CharactersDiffCallback(
        private val oldCharacters: List<CharacterDTO>,
        private val newCharacters: List<CharacterDTO>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldCharacters.size
        }

        override fun getNewListSize(): Int {
            return newCharacters.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCharacters[oldItemPosition].id == newCharacters[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldCharacters[oldItemPosition].url == newCharacters[newItemPosition].url
        }
    }

    interface OnCharacterListener {
        fun onCharacterClick(character: CharacterDTO)
    }

}