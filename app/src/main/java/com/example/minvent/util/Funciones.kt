package com.example.minvent.util

inline fun ejecutarConValidacion(condicion: Boolean, accion: () -> Unit) {
    if (condicion) {
        accion()
    }
}