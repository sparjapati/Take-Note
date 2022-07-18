package com.takeNote.feature_note.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.takeNote.ui.theme.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
) : Parcelable {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)