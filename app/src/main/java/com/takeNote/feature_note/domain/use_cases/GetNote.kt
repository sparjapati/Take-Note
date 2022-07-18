package com.takeNote.feature_note.domain.use_cases

import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNote @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? = withContext(Dispatchers.IO){ repository.getNoteById(id)}
}