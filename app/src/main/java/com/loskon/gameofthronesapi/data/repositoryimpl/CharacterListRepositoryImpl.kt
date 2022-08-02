package com.loskon.gameofthronesapi.data.repositoryimpl

import com.loskon.gameofthronesapi.data.networkdatasource.NetworkDataSource
import com.loskon.gameofthronesapi.data.networkdatasource.dto.toCharacter
import com.loskon.gameofthronesapi.domain.model.Character
import com.loskon.gameofthronesapi.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : CharacterListRepository {

    override suspend fun getCharactersPairAsFlow(): Flow<Pair<Boolean, List<Character>>>  {
        return networkDataSource.getCharactersListAsFlow().map { characters ->
           characters.first to characters.second.map { it.toCharacter() }
        }
    }
}