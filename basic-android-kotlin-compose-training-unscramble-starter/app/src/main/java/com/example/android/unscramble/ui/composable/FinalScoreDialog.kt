package com.example.android.unscramble.ui.composable

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.android.unscramble.R

@Composable
fun FinalScoreDialog(
    state: FinalScoreDialogComposable.State,
    listener: FinalScoreDialogComposable.Listener,
    modifier: Modifier = Modifier
) {
    SideEffect { println("[TEST] compose FinalScoreDialog") }

    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, state.score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = listener.onPlayAgain
            ) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}

class FinalScoreDialogComposable {
    data class State(
        val score: Int,
    ) {
        companion object {
            val initialState = State(
                score = 0
            )
        }
    }

    data class Listener(
        val onPlayAgain: () -> Unit,
    )
}