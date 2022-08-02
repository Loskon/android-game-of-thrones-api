package com.loskon.gameofthronesapi.domain.interactor

import com.loskon.gameofthronesapi.domain.model.Character
import com.loskon.gameofthronesapi.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterListInteractor @Inject constructor(
    private val characterRepository: CharacterListRepository
) {

    suspend fun getCharactersPairAsFlow(): Flow<Pair<Boolean, List<Character>>> {
        return characterRepository.getCharactersPairAsFlow()
    }
}