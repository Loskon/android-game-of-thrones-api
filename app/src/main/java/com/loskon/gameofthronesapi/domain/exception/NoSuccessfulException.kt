package com.loskon.gameofthronesapi.domain.exception

import java.io.IOException

class NoSuccessfulException(private val code: Int) : IOException() {
    override val message: String get() = code.toString()
}