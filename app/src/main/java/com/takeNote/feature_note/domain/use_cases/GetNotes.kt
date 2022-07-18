package com.takeNote.feature_note.domain.use_cases

import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.repository.NoteRepository
import com.takeNote.feature_note.domain.utils.NoteOrder
import com.takeNote.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class GetNotes(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Time(OrderType.Descending)): Flow<List<Note>> = repository.getAllNotes().map { notes ->
        when (noteOrder.orderType) {
            OrderType.Ascending -> {
                when (noteOrder) {
                    is NoteOrder.Time -> {
                        notes.sortedBy { it.timeStamp }
                    }
                    is NoteOrder.Color -> {
                        notes.sortedBy { it.color }
                    }
                    is NoteOrder.Title -> {
                        notes.sortedBy { it.title.lowercase(Locale.getDefault()) }
                    }
                }
            }
            OrderType.Descending -> {
                when (noteOrder) {
                    is NoteOrder.Time -> {
                        notes.sortedByDescending { it.timeStamp }
                    }
                    is NoteOrder.Color -> {
                        notes.sortedByDescending { it.color }
                    }
                    is NoteOrder.Title -> {
                        notes.sortedByDescending { it.title.lowercase(Locale.getDefault()) }
                    }
                }
            }
        }
    }
}