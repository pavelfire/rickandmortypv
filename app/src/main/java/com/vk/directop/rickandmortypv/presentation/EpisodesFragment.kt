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
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentEpisodesBinding
    private lateinit var episodeAdapter: EpisodeAdapter

    private val episodesViewModel: EpisodesViewModel by viewModels {
        EpisodesViewModel.EpisodesViewModelFactory(
            ((requireActivity().application) as App).getEpisodesUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        episodeAdapter = EpisodeAdapter(
            object : EpisodeAdapter.OnEpisodeListener {
                override fun onEpisodeClick(episode: EpisodeDTO) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, EpisodeDetailFragment.newInstance(episode)
                        )
                        .addToBackStack(null)
                        .commit()
                }
            })

        episodesViewModel.getEpisodes(episodesViewModel.searchFilter.value.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEpisodesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {
                episodesViewModel.searchName(query, false)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                episodesViewModel.searchName(query, true)
                return false
            }
        })

        episodesViewModel.episodes.observe(viewLifecycleOwner) {
            episodeAdapter.submitUpdate(it)
        }

        episodesViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
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
            adapter = episodeAdapter
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        }

        episodesViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.an_error_has_occurred, it),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getTitleRes(): Int = R.string.episodes

    companion object {
        const val COLUMNS_COUNT = 2
    }
}