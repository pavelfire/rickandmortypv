package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.RetrofitInstance
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character.CharacterRM
import com.vk.directop.rickandmortypv.databinding.FragmentCharactersBinding
import retrofit2.HttpException
import java.io.IOException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharactersFragment : Fragment(), HasCustomTitle{

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterAdapter: CharacterAdapter

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharactersBinding.inflate(inflater)

//        navigator().listenResult(Options::class.java)
//        binding.searchView.setOnClickListener {
//
//        }

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getCharacters()
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
                characterAdapter.characters = response.body()!!.results
            }else{
                Log.d("TAG", "Response not successful")
            }
            binding.progressBar.isVisible = false
            binding.tvError.isVisible = false
        }
        return binding.root
    }

    override fun getTitleRes(): Int = R.string.characters

    private fun setupRecyclerView() = binding.list.apply {
        characterAdapter = CharacterAdapter(
            object: CharacterAdapter.OnCharacterListener{
                override fun onCharacterClick(character: CharacterRM) {
                    Log.d("TAG", "Clicked ${character.name}")
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, CharacterDetailFragment.newInstance(
                                character
                            )
                        )
                        .addToBackStack(null)
                        .commit()
                }

            }
        )
        adapter = characterAdapter
        layoutManager = GridLayoutManager(context, 2) // LinearLayoutManager(context)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LocationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}