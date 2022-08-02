package com.loskon.gameofthronesapi.app.presentation.screens

import com.loskon.gameofthronesapi.app.base.presentation.viewmodel.BaseViewModel
import com.loskon.gameofthronesapi.app.presentation.screens.state.CharacterListState
import com.loskon.gameofthronesapi.domain.interactor.CharacterListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : BaseViewModel() {

    private val characterListState = MutableStateFlow(CharacterListState())
    val getCharacterListState get() = characterListState.asStateFlow()

    fun performCharactersRequest() {
        launchErrorJob {
            characterListInteractor.getCharactersPairAsFlow().collectLatest { charactersPair ->
                characterListState.emit(
                    CharacterListState(fromCache = charactersPair.first, characters = charactersPair.second)
                )
            }
        }
    }
}