package com.loskon.gameofthronesapi.domain.exception

import java.io.IOException

class EmptyCacheException : IOException() {
    override val message: String get() = "Cache is empty"
}