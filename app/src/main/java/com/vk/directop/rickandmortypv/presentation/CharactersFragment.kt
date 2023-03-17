package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.directop.rickandmortypv.R
import com.vk.directop.rickandmortypv.app.App
import com.vk.directop.rickandmortypv.contract.HasCustomTitle
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.databinding.FragmentCharactersBinding
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment(), HasCustomTitle{

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val editTextSubject = PublishSubject.create<String>()

    private val charactersViewModel: CharactersViewModel by viewModels {
        CharactersViewModel.CharactersViewModelFactory(
            ((requireActivity().application) as App).getCharactersUseCase,
            //((requireActivity().application) as App).getSavedCharactersUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterAdapter = CharacterAdapter(
            //requireContext(),
            object : CharacterAdapter.OnCharacterListener {
                override fun onCharacterClick(character: CharacterDTO) {
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
            })

        charactersViewModel.getCharacters()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater)
        //setupRecyclerView()
        //debunceSearch(binding)
        //initialLoad(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersViewModel.characters.observe(viewLifecycleOwner) {
            //characterAdapter.submitUpdate(it)
            characterAdapter.characters = it
        }

        charactersViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> {
//                    LayoutUtils.crossFade(binding.progressBar, binding.list)
                    binding.progressBar.visibility = View.VISIBLE
                    binding.list.visibility = View.INVISIBLE
                }
                false -> {
//                    LayoutUtils.crossFade(binding.list, binding.progressBar)
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.list.visibility = View.VISIBLE
                }
            }
        }

        list.apply {
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