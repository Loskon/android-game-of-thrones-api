package com.loskon.gameofthronesapi.app.presentation.screens

import com.loskon.gameofthronesapi.app.base.presentation.viewmodel.BaseViewModel
import com.loskon.gameofthronesapi.app.presentation.screens.state.CharacterListUiState
import com.loskon.gameofthronesapi.domain.interactor.CharacterListInteractor
import com.loskon.gameofthronesapi.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : BaseViewModel() {

    private val characterListState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val getCharacterListState get() = characterListState.asStateFlow()

    private var job: Job? = null

    fun performCharactersRequest() {
        job?.cancel()
        job = launchErrorJob(errorBlock = { tryEmitError(it) }) {
            characterListState.emit(CharacterListUiState.Loading)
            characterListInteractor.getCharactersPairAsFlow().collectLatest { setCharacterListState(it) }
        }
    }

    private suspend fun setCharacterListState(pair: Pair<Boolean, List<Character>>) {
        characterListState.emit(CharacterListUiState.Success(fromCache = pair.first, characters = pair.second))
    }

    private fun tryEmitError(throwable: Throwable) {
        characterListState.tryEmit(CharacterListUiState.Error(throwable))
    }
}