package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character.CharacterRM
import com.vk.directop.rickandmortypv.databinding.FragmentCharacterDetailBinding

private const val ARG_CHARACTER = "ARG_CHARACTER"

class CharacterDetailFragment : Fragment() {

    lateinit var binding: FragmentCharacterDetailBinding

    private var characterRM: CharacterRM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterRM = it.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = characterRM!!.name
            tvGender.text = characterRM!!.gender
            tvStatus.text = characterRM!!.status
            tvSpecies.text = characterRM!!.species
            tvEpisode.text = characterRM!!.episode.toString()

            if (characterRM!!.image.isNotBlank()) {
                Glide.with(image.context)
                    .load(characterRM!!.image)
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
        fun newInstance(characterRM: CharacterRM) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, characterRM)
                }
            }
    }
}