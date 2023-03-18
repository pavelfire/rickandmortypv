package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.databinding.FragmentCharacterDetailBinding

private const val ARG_CHARACTER = "ARG_CHARACTER"

class EpisodeDetailFragment : Fragment() {

    lateinit var binding: FragmentCharacterDetailBinding

    private var episodeDTO: EpisodeDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            episodeDTO = it.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = episodeDTO!!.name
            tvGender.text = episodeDTO!!.air_date
            tvStatus.text = episodeDTO!!.created
            tvSpecies.text = episodeDTO!!.episode
            tvEpisode.text = episodeDTO!!.characters.toString()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(episodeDTO: EpisodeDTO) =
            EpisodeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, episodeDTO)
                }
            }
    }
}