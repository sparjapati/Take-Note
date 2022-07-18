package com.takeNote.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.takeNote.feature_note.domain.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val NOTE_DATABASE_NAME = "note_database.db"
    }
}