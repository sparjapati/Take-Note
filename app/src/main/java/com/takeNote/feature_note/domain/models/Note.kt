package com.takeNote.feature_note.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.takeNote.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
