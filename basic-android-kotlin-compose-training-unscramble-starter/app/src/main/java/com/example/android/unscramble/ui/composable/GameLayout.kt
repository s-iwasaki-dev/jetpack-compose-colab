package com.example.android.unscramble.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.unscramble.R

@Composable
fun GameLayout(
    state: GameLayoutComposable.State,
    listener: GameLayoutComposable.Listener,
    modifier: Modifier = Modifier
) {
    SideEffect { println("[TEST] compose GameLayout") }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),

        ) {
        Text(
            text = state.currentScrambledWord,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.instructions),
            fontSize = 17.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = state.userGuess,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = listener.onUserGuessChanged,
            label = {
                if (state.isGuessWrong) {
                    Text(stringResource(R.string.wrong_guess))
                } else {
                    Text(stringResource(R.string.enter_your_word))
                }
            },
            isError = state.isGuessWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { listener.onKeyboardDone() }
            ),
        )
    }
}

class GameLayoutComposable {
    data class State(
        val currentScrambledWord: String,
        val isGuessWrong: Boolean,
        val userGuess: String,
    ) {
        companion object {
            val initialState = State(
                currentScrambledWord = "",
                isGuessWrong = false,
                userGuess = "",
            )
        }
    }

    data class Listener(
        val onUserGuessChanged: (String) -> Unit,
        val onKeyboardDone: () -> Unit,
    )
}