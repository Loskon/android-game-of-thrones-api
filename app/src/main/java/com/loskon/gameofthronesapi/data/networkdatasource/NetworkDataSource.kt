package com.loskon.gameofthronesapi.data.networkdatasource

import com.loskon.gameofthronesapi.data.networkdatasource.api.CharacterApi
import com.loskon.gameofthronesapi.data.networkdatasource.dto.CharacterDto
import com.loskon.gameofthronesapi.data.networkdatasource.interceptor.CacheInterceptor
import com.loskon.gameofthronesapi.domain.exception.NoSuccessfulException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val apiInterface: CharacterApi
) {

    suspend fun getCharactersListAsFlow(): Flow<Pair<Boolean, List<CharacterDto>>> {
        return flow {
            val response = apiInterface.getCharacters()

            if (response.isSuccessful) {
                val fromCache = response.headers()[CacheInterceptor.CACHE_HEADER].toBoolean()
                emit(fromCache to (response.body() ?: emptyList()))
            } else {
                throw NoSuccessfulException(response.code())
            }
        }
    }

/*    suspend fun getCharacterAsFlow(id: Int): Flow<CharacterDto> {
        return flow {
            val response = apiInterface.getCharacter(id)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    emit(body)
                } else {
                    throw NullPointerException("No such data: " + response.code()) // TODO Ошибка
                }
            } else {
                throw NoSuccessfulException(response.code())
            }
        }
    }*/
}