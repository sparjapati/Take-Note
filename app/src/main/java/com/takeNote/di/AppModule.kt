package com.takeNote.di

import android.app.Application
import androidx.room.Room
import com.takeNote.feature_note.data.data_source.NoteDatabase
import com.takeNote.feature_note.data.repository.NoteRepositoryImpl
import com.takeNote.feature_note.domain.repository.NoteRepository
import com.takeNote.feature_note.domain.use_cases.DeleteNote
import com.takeNote.feature_note.domain.use_cases.GetNotes
import com.takeNote.feature_note.domain.use_cases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) {
        Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.NOTE_DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase) = NoteRepositoryImpl(noteDatabase.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotes(repository),
            DeleteNote(repository)
        )
    }
}