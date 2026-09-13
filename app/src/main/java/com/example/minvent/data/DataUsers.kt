package com.example.minvent.data

import androidx.compose.runtime.mutableStateListOf


object DataUsers {
    val dummyData = mutableStateListOf(
        User("juan@correo.com", "1234"),
        User("pedro@correo.com", "1234"),
        User("maria@correo.com", "1234"),
        User("camila@correo.com", "1234"),
        User("ignacio@correo.com", "1234")
    )

    fun addUser(nuevoUser: User) {
        dummyData += nuevoUser
    }
}