package com.takeNote.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.takeNote.feature_note.presentation.util.Screen
import com.takeNote.ui.theme.TakeNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeNoteTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NotesScreen.route) {
                        composable(Screen.NotesScreen.route) {
                            com.takeNote.feature_note.presentation.note.NotesScreen(navController)
                        }
                        composable(
                            Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument("noteColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            com.takeNote.feature_note.presentation.add_edit_note.AddEditNoteScreen(
                                navController,
                                noteColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}