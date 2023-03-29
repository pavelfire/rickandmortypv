package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.databinding.FragmentEpisodeDetailBinding

private const val ARG_CHARACTER = "ARG_CHARACTER"

class EpisodeDetailFragment : Fragment() {

    lateinit var binding: FragmentEpisodeDetailBinding

    private var episodeDTO: EpisodeDto? = null

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

        binding = FragmentEpisodeDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = episodeDTO!!.name
            tvAirDate.text = episodeDTO!!.air_date
            tvCreated.text = episodeDTO!!.created
            tvEpisode.text = episodeDTO!!.episode
            tvCharacters.text = episodeDTO!!.characters.toString()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(episodeDTO: EpisodeDto) =
            EpisodeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, episodeDTO)
                }
            }
    }
}