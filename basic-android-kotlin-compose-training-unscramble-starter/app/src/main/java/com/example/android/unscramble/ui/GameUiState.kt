package com.example.android.unscramble.ui

data class GameUiState(
    val userGuess: String = "",
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 0,
    val score: Int = 1,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)