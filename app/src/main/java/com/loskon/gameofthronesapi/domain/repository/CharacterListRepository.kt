package com.loskon.gameofthronesapi.domain.repository

import com.loskon.gameofthronesapi.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    suspend fun getCharactersPairAsFlow(): Flow<Pair<Boolean, List<Character>>>
}