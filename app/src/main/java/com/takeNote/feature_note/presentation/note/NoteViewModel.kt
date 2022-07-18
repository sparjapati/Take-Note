package com.takeNote.feature_note.presentation.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takeNote.feature_note.domain.models.Note
import com.takeNote.feature_note.domain.use_cases.NoteUseCases
import com.takeNote.feature_note.domain.utils.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {

    private val _noteState = mutableStateOf(NoteState())
    val noteState: State<NoteState> = _noteState
    private var getNoteJob: Job? = null
    private var recentlyDeletedNote: Note? = null

    init {
        getNotes(noteState.value.noteOrder)
    }


    fun onEvent(noteEvent: NoteEvent) {
        when (noteEvent) {
            is NoteEvent.DeleteNoteEvent -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(noteEvent.note)
                    recentlyDeletedNote = noteEvent.note
                }
            }
            is NoteEvent.Order -> {
                if (noteState.value.noteOrder::class == noteEvent.noteOrder::class &&
                    noteState.value.noteOrder.orderType == noteEvent.noteOrder.orderType
                ) {
                    return
                }
                getNotes(noteEvent.noteOrder)
            }
            NoteEvent.RestoreDeletedNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            NoteEvent.ToggleOrderSection -> {
                _noteState.value = noteState.value.copy(isOrderSectionVisible = !noteState.value.isOrderSectionVisible)
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNoteJob?.cancel()
        getNoteJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _noteState.value = noteState.value.copy(notes = notes, noteOrder = noteOrder)
        }.launchIn(viewModelScope)
    }

}