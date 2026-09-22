package com.example.minvent.util


fun String.toCleanEmail(): String = this.trim().lowercase()


fun String.isNotBlankValid(): String? {
    return this.ifBlank { null }
}