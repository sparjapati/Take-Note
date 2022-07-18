package com.takeNote.feature_note.domain.use_cases

import com.takeNote.feature_note.domain.models.InvalidNoteException
import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class AddNote @Inject constructor(private val repository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("The title of note can't be empty!!")
        else if (note.content.isBlank())
            throw InvalidNoteException("The content of note can't be empty!!")
        repository.insertNote(note)
    }
}