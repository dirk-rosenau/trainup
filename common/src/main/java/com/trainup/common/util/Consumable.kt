package com.trainup.common.util

data class Consumable<T>(val data: T) {
    private var consumed: Boolean = false

    fun isConsumed() = consumed

    fun consume() {
        consumed = true
    }
}

inline infix fun <T> Consumable<T>?.invokeIfNotConsumed(closure: (T) -> Unit) {
    if (this != null && !this.isConsumed()) {
        consume()
        closure(data)
    }
}
