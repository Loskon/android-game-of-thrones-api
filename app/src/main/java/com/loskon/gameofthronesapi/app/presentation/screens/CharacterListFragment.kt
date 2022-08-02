package com.loskon.gameofthronesapi.app.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.base.extension.flow.observe
import com.loskon.gameofthronesapi.app.presentation.screens.adapter.CharacterListAdapter
import com.loskon.gameofthronesapi.databinding.FragmeentCharacterListBinding
import com.loskon.gameofthronesapi.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragmeent_character_list) {

    private val binding by viewBinding(FragmeentCharacterListBinding::bind)
    private val viewModel: CharacterListViewModel by viewModels()

    private val charactersAdapter = CharacterListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) viewModel.performCharactersRequest()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        setupViewsListener()
        installObserver()
    }

    private fun configureRecyclerView() {
        with(binding.rvCharacterList) {
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupViewsListener() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.performCharactersRequest()
            binding.refreshLayout.isRefreshing = false
        }
        binding.bottomBar.setNavigationOnClickListener {

        }
        charactersAdapter.setOnItemClickListener {

        }
    }

    private fun installObserver() {
        viewModel.getCharacterListState.observe(viewLifecycleOwner) { characterListState ->
            if (characterListState.characters.isNotEmpty()) charactersAdapter.setCharacterList(characterListState.characters)
        }
    }
}