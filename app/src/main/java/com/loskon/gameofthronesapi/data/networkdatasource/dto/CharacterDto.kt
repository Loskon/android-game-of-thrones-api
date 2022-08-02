package com.loskon.gameofthronesapi.data.networkdatasource.dto

import com.loskon.gameofthronesapi.domain.model.Character

data class CharacterDto(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val fullName: String? = null,
    val title: String? = null,
    val family: String? = null,
    val image: String? = null,
    val imageUrl: String? = null
)

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id ?: 0,
        fullName = fullName ?: "",
        title = title ?: "",
        family = family ?: "",
        imageUrl = imageUrl ?: ""
    )
}