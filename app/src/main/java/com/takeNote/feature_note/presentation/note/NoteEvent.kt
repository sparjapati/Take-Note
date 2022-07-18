package com.takeNote.feature_note.presentation.note

import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.utils.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNoteEvent(val note: Note) : NoteEvent()
    object RestoreDeletedNote : NoteEvent()
    object ToggleOrderSection : NoteEvent()
}
