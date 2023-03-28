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
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.app.App
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.databinding.FragmentCharactersBinding
import javax.inject.Inject

class CharactersFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var charactersViewModelFactory: CharactersViewModel.CharactersViewModelFactory

    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ((requireActivity().application) as App).appComponent.inject(this)

        charactersViewModel = ViewModelProvider(this, charactersViewModelFactory)
            .get(CharactersViewModel::class.java)

        characterAdapter = CharacterAdapter(
            object : CharacterAdapter.OnCharacterListener {
                override fun onCharacterClick(character: CharacterDTO) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, CharacterDetailFragment.newInstance(character)
                        )
                        .addToBackStack(null)
                        .commit()
                }
            })

        charactersViewModel.getCharacters(charactersViewModel.searchFilter.value.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String): Boolean {
                charactersViewModel.searchName(query, false)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                charactersViewModel.searchName(query, true)
                return false
            }
        })

        charactersViewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.submitUpdate(it)
        }

        charactersViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
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
            adapter = characterAdapter
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        }

        charactersViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.an_error_has_occurred, it),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getTitleRes(): Int = R.string.characters

    companion object {
        const val COLUMNS_COUNT = 2
    }

}