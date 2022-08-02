package com.loskon.gameofthronesapi.data.networkdatasource.api

import com.loskon.gameofthronesapi.data.networkdatasource.dto.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {

    @GET("/api/v2/Characters")
    suspend fun getCharacters(): Response<List<CharacterDto>>

    @GET("/api/v2/Characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterDto>
}