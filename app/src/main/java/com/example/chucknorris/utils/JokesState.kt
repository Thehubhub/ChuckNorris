package com.example.chucknorris.utils

sealed class JokesState{
    object LOADING: JokesState()
    class SUCCESS<T>(val jokes: T, isLocalData: Boolean = false): JokesState()
    class ERROR(val error: Throwable): JokesState()
}
