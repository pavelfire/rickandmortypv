package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.RetrofitInstance
import com.vk.directop.rickandmortypv.databinding.FragmentLocationsBinding
import retrofit2.HttpException
import java.io.IOException

class LocationsFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentLocationsBinding
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLocationsBinding.inflate(inflater)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getLocations()
            } catch (e: IOException) {
                Log.d("TAG", "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                binding.tvError.isVisible = true
                binding.tvError.text = "IOException, you might not have internet connection"
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("TAG", "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                binding.tvError.isVisible = true
                binding.tvError.text = "HttpException, unexpected response"
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                locationAdapter.locations = response.body()!!.results
            }else{
                Log.d("TAG", "Response not successful")
            }
            binding.progressBar.isVisible = false
            binding.tvError.isVisible = false
        }
        return binding.root
    }

    override fun getTitleRes(): Int = R.string.locations

    private fun setupRecyclerView() = binding.list.apply {
        locationAdapter = LocationAdapter()
        adapter = locationAdapter
        layoutManager = GridLayoutManager(context, 2) // LinearLayoutManager(context)
    }

}