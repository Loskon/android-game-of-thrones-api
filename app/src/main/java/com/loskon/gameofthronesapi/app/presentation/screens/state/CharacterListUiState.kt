package com.loskon.gameofthronesapi.app.presentation.screens.state

import com.loskon.gameofthronesapi.domain.model.Character

sealed class CharacterListUiState {
    data class Success(val fromCache: Boolean, val characters: List<Character>) : CharacterListUiState()
    data class Error(val throwable: Throwable) : CharacterListUiState()
    object Loading : CharacterListUiState()
}