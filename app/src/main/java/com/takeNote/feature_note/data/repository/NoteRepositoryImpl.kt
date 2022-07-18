package com.takeNote.feature_note.data.repository

import com.takeNote.feature_note.data.data_source.NoteDao
import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(val dao: NoteDao) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Long): Note? = dao.getNoteById(id)

    override suspend fun insertNote(note: Note) = dao.insertNote(note)

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}