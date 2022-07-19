package com.takeNote.feature_note.presentation.add_edit_note

import com.takeNote.feature_note.domain.models.Note

data class AddEditScreenNavArgs(
    val note: Note? = null,
)