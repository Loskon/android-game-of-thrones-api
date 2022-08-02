package com.loskon.gameofthronesapi.app.presentation.screens.state

import com.loskon.gameofthronesapi.domain.model.Character

data class CharacterListState(
    val fromCache: Boolean = false,
    val characters: List<Character> = emptyList()
)