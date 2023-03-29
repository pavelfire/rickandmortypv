package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.app.App
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.databinding.FragmentLocationsBinding
import javax.inject.Inject

class LocationsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var locationAdapter: LocationAdapter

    @Inject
    lateinit var locationsViewModelFactory: LocationsViewModel.LocationsViewModelFactory

    private lateinit var locationsViewModel: LocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ((requireActivity().application) as App).appComponent.inject(this)

        locationsViewModel = ViewModelProvider(this, locationsViewModelFactory)
            .get(LocationsViewModel::class.java)

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
            })

        //locationsViewModel.getLocations(locationsViewModel.searchFilter.value.toString())
        locationsViewModel.getLocationsRx(locationsViewModel.searchFilter.value.toString())
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

        val layoutManager = binding.list.layoutManager as LinearLayoutManager
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                locationsViewModel.scrollMore(
                    totalItemCount,
                    visibleItemCount,
                    lastVisibleItem
                )
            }
        })

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