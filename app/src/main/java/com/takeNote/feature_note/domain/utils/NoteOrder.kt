package com.takeNote.feature_note.domain.utils

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Time(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Color -> Color(orderType)
            is Time -> Time(orderType)
            is Title -> Title(orderType)
        }
    }
}
