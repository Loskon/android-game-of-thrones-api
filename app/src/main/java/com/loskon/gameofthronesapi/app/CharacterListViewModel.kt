package com.loskon.gameofthronesapi.app

import com.loskon.gameofthronesapi.app.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterListViewModel : BaseViewModel() {

    private val characterListState = MutableStateFlow<List<Character>>(emptyList())
    val getCharacterListState get() = characterListState.asStateFlow()

    init {
        val list = mutableListOf<Character>().apply { add(Character(fullName = "List")) }

        launchErrorJob { characterListState.emit(list) }
    }
}