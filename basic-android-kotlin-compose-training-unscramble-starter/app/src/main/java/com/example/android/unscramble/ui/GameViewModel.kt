package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import com.example.android.unscramble.ui.composable.GameLayoutComposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState.initialState)
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS){
            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update {
                it.copy(
                    gameStatusState = it.gameStatusState.copy(
                        score = updatedScore
                    ),
                    gameLayoutState = it.gameLayoutState.copy(
                        isGuessWrong = false
                    ),
                    finalScoreDialogState = it.finalScoreDialogState.copy(
                        score = updatedScore
                    ),
                    isGameOver = true
                )
            }
        } else{
            // Normal round in the game
            _uiState.update {
                it.copy(
                    gameStatusState = it.gameStatusState.copy(
                        score = updatedScore,
                        wordCount = it.gameStatusState.wordCount.inc(),
                    ),
                    gameLayoutState = it.gameLayoutState.copy(
                        currentScrambledWord = pickRandomWordAndShuffle(),
                        isGuessWrong = false
                    )
                )
            }
        }
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.update {
            GameUiState.initialState.copy(
                gameLayoutState = GameLayoutComposable.State.initialState.copy(
                    currentScrambledWord = pickRandomWordAndShuffle()
                )
            )
        }
    }

    fun updateUserGuess(guessedWord: String){
        _uiState.update {
            it.copy(
                gameLayoutState = it.gameLayoutState.copy(
                    userGuess = guessedWord
                )
            )
        }
    }

    fun checkUserGuess() {
        if (_uiState.value.gameLayoutState.userGuess.equals(currentWord, ignoreCase = true)) {
            // User's guess is correct, increase the score
            val updatedScore = _uiState.value.gameStatusState.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // User's guess is wrong, show an error
            _uiState.update {
                it.copy(
                    gameLayoutState = it.gameLayoutState.copy(
                        isGuessWrong = true
                    ),
                )
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    fun skipWord() {
        updateGameState(_uiState.value.gameStatusState.score)
        // Reset user guess
        updateUserGuess("")
    }
}