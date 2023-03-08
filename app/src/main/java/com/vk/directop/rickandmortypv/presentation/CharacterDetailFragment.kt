package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.databinding.FragmentCharacterDetailBinding

private const val ARG_CHARACTER = "ARG_CHARACTER"

class CharacterDetailFragment : Fragment() {

    lateinit var binding: FragmentCharacterDetailBinding

    private var characterDTO: CharacterDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterDTO = it.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = characterDTO!!.name
            tvGender.text = characterDTO!!.gender
            tvStatus.text = characterDTO!!.status
            tvSpecies.text = characterDTO!!.species
            tvEpisode.text = characterDTO!!.episode.toString()

            if (characterDTO!!.image.isNotBlank()) {
                Glide.with(image.context)
                    .load(characterDTO!!.image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_characters)
                    .error(R.drawable.ic_error_load)
                    .into(image)
            } else {
                image.setImageResource(R.drawable.ic_characters)
            }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(characterDTO: CharacterDTO) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, characterDTO)
                }
            }
    }
}