package com.takeNote.feature_note.presentation.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.takeNote.feature_note.domain.utils.NoteOrder
import com.takeNote.feature_note.domain.utils.OrderType

@Composable
fun NoteOrderSection(modifier: Modifier = Modifier, noteOrder: NoteOrder, onOrderChanged: (NoteOrder) -> Unit) {
    Column(modifier = modifier) {
        Row(modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                isSelected = noteOrder is NoteOrder.Title,
                onSelected = { onOrderChanged(NoteOrder.Title(noteOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Time",
                isSelected = noteOrder is NoteOrder.Time,
                onSelected = { onOrderChanged(NoteOrder.Time(noteOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                isSelected = noteOrder is NoteOrder.Color,
                onSelected = { onOrderChanged(NoteOrder.Color(noteOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                isSelected = noteOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChanged(noteOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                isSelected = noteOrder.orderType is OrderType.Descending,
                onSelected = {
                    onOrderChanged(noteOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}