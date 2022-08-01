package com.loskon.gameofthronesapi.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.loskon.gameofthronesapi.R
import com.loskon.gameofthronesapi.app.adapter.CharacterAdapter
import com.loskon.gameofthronesapi.app.base.extension.flow.observe
import com.loskon.gameofthronesapi.databinding.FragmeentCharacterListBinding
import com.loskon.gameofthronesapi.viewbinding.viewBinding
import javax.inject.Inject

class CharacterListFragment : Fragment(R.layout.fragmeent_character_list) {

    private val binding by viewBinding(FragmeentCharacterListBinding::bind)

    //private val viewModel: CharacterListViewModel by viewModels()
    @Inject
    lateinit var viewModel: CharacterListViewModel

    private val characterAdapter = CharacterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        installObserver()
    }

    private fun configureRecyclerView() {
        /*        with(binding.rvUserList) {
                    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = characterAdapter
                    setHasFixedSize(true)
                }*/
    }

    private fun installObserver() {
        viewModel.getCharacterListState.observe(viewLifecycleOwner) { characters ->
            if (characters.isNotEmpty()) binding.tv.text = characters[0].fullName
        }
    }
}