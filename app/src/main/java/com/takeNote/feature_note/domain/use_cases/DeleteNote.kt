package com.takeNote.feature_note.domain.use_cases

import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = withContext(Dispatchers.IO) { repository.deleteNote(note) }
}