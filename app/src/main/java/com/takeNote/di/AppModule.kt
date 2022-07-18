package com.takeNote.di

import android.app.Application
import androidx.room.Room
import com.takeNote.feature_note.data.data_source.NoteDatabase
import com.takeNote.feature_note.data.repository.NoteRepositoryImpl
import com.takeNote.feature_note.domain.repository.NoteRepository
import com.takeNote.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(app: Application) = Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.NOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDatabase: NoteDatabase):NoteRepository = NoteRepositoryImpl(noteDatabase.noteDao)

    @Singleton
    @Provides
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotes(repository),
            DeleteNote(repository),
            AddNote(repository),
            GetNote(repository)
        )
    }
}