package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.app.App
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var locationAdapter: LocationAdapter

    private val locationsViewModel: LocationsViewModel by viewModels {
        LocationsViewModel.LocationsViewModelFactory(
            ((requireActivity().application) as App).getLocationsUseCase,
            ((requireActivity().application) as App).getLocationsRxUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationAdapter = LocationAdapter(
            object : LocationAdapter.OnLocationListener {
                override fun onLocationClick(location: LocationDTO) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, LocationDetailFragment.newInstance(location)
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        locationsViewModel.getLocations(locationsViewModel.searchFilter.value.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLocationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {
                locationsViewModel.searchName(query, false)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                locationsViewModel.searchName(query, true)
                return false
            }
        })

        locationsViewModel.locations.observe(viewLifecycleOwner) {
            locationAdapter.submitUpdate(it)
        }

        locationsViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.list.visibility = View.INVISIBLE
                }
                false -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.list.visibility = View.VISIBLE
                }
            }
        }

        binding.list.apply {
            adapter = locationAdapter
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        }

        locationsViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.an_error_has_occurred, it),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getTitleRes(): Int = R.string.locations

    companion object {
        const val COLUMNS_COUNT = 2
    }
}