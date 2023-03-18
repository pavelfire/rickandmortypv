package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.databinding.FragmentCharacterDetailBinding

private const val ARG_CHARACTER = "ARG_CHARACTER"

class LocationDetailFragment : Fragment() {

    lateinit var binding: FragmentCharacterDetailBinding
    private var locationDTO: LocationDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locationDTO = it.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = locationDTO!!.name
            tvGender.text = locationDTO!!.dimension
            tvStatus.text = locationDTO!!.created
            tvSpecies.text = locationDTO!!.type
            tvEpisode.text = locationDTO!!.residents.toString()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(locationDTO: LocationDTO) =
            LocationDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CHARACTER, locationDTO)
                }
            }
    }
}