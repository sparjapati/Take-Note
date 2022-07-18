package com.takeNote.feature_note.presentation.note

import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.utils.NoteOrder
import com.takeNote.feature_note.domain.utils.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Time(OrderType.Descending),
    val isOrderSectionVisible: Boolean = true,
)
