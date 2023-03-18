package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.app.App
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.RetrofitInstance
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.databinding.FragmentEpisodesBinding
import kotlinx.android.synthetic.main.fragment_characters.*
import retrofit2.HttpException
import java.io.IOException

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
            object : EpisodeAdapter.OnEpisodeListener{
                override fun onEpisodeClick(episode: EpisodeDTO) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, EpisodeDetailFragment.newInstance(
                                episode
                            )
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        episodesViewModel.getEpisodes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEpisodesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodesViewModel.episodes.observe(viewLifecycleOwner) {
            episodeAdapter.episodes = it
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

    fun delBeforeCommit() {

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getEpisodes()
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
                episodeAdapter.episodes = response.body()!!.results
            } else {
                Log.d("TAG", "Response not successful")
            }
            binding.progressBar.isVisible = false
            binding.tvError.isVisible = false
        }
    }
}