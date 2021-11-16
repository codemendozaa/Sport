package com.example.sport.core

import java.lang.Exception

sealed class Resource<out T> {
    class Loading<out T>:Resource<T>()
    data class Success<out T>(val data:T):Resource<T>()
    data class Failure(val excepcion: Exception):Resource<Nothing>()
}
