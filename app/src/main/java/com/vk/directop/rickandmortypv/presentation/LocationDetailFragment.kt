package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.databinding.FragmentLocationDetailBinding

private const val ELEMENT = "ELEMENT"

class LocationDetailFragment : Fragment() {

    lateinit var binding: FragmentLocationDetailBinding
    private var locationDTO: LocationDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locationDTO = it.getParcelable(ELEMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLocationDetailBinding.inflate(inflater)

        with(binding) {
            tvName.text = locationDTO!!.name
            tvDimension.text = locationDTO!!.dimension
            tvCreated.text = locationDTO!!.created
            tvType.text = locationDTO!!.type
            tvResidents.text = locationDTO!!.residents.toString()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(locationDTO: LocationDTO) =
            LocationDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ELEMENT, locationDTO)
                }
            }
    }
}