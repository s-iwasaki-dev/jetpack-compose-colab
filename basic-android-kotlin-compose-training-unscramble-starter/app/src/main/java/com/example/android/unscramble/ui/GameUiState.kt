package com.example.android.unscramble.ui

import com.example.android.unscramble.ui.composable.FinalScoreDialogComposable
import com.example.android.unscramble.ui.composable.GameLayoutComposable
import com.example.android.unscramble.ui.composable.GameStatusComposable

data class GameUiState(
    val gameStatusState: GameStatusComposable.State,
    val gameLayoutState: GameLayoutComposable.State,
    val finalScoreDialogState: FinalScoreDialogComposable.State,
    val isGameOver: Boolean
) {
    companion object {
        val initialState = GameUiState(
            gameStatusState = GameStatusComposable.State.initialState,
            gameLayoutState = GameLayoutComposable.State.initialState,
            finalScoreDialogState = FinalScoreDialogComposable.State.initialState,
            isGameOver = false
        )
    }
}