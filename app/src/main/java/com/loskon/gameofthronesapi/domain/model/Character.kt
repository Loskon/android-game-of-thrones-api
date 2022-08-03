package com.loskon.gameofthronesapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int = 0,
    val fullName: String = "",
    val title: String = "",
    val family: String = "",
    val imageUrl: String = ""
) : Parcelable