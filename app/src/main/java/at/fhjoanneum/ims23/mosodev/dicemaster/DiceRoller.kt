package at.fhjoanneum.ims23.mosodev.dicemaster

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement.Vertical
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.Alignment


@Composable
fun DiceSelector(diceTypes: List<String>, onDiceSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Gray)
            .padding(8.dp)
            .clickable { expanded = true }
    ) {
        Text(text = "Select a dice")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        diceTypes.forEach { dice ->
            DropdownMenuItem(onClick = {
                expanded = false
                onDiceSelected(dice)
            }, text = {
                Text(text = dice)
            })
        }
    }
}

@Composable
fun DiceRoller(selectedDice: List<String>, onRoll: () -> List<Int>) {
    Button(onClick = {
        val results = onRoll()
    }) {
        Text("Roll!")
    }
}
@Preview
@Composable
fun MyButton() {
    var buttonText by remember { mutableStateOf("How to roll") }

    Column(
        modifier = Modifier.fillMaxSize(),


    ) {
        Button(
            onClick = {
                buttonText = "Wait a minute!"
            },
            modifier = Modifier.padding(16.dp)
                .align(Alignment.End)
        ) {
            Text(text = buttonText)


        }
    }
}


@Composable
fun MyComposeDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            // This will be called when you click outside the dialog or press back
            onDismiss()
        },
        title = { Text("Dice Master Instructions") },
        text = { Text("You can throw any dice you like. Just select a dice from the top list " +
                "and click roll. It will appear and you get random numbers in the range of the dice by clicking on Roll button"
        + " Have fun!") },
        confirmButton = {
            Button(
                onClick = {
                    // Handle OK button click
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    // Handle Cancel button click
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}




@Composable
fun DiceDisplay(selectedDice: List<String>, results: List<Int> = emptyList()) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            selectedDice.forEach { die ->
                Text(text = die, modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            results.forEach { result ->
                Text(text = result.toString(), modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun MainDiceRoller() {
    val diceTypes = listOf("d2", "d4", "d6", "d8", "d10", "d12", "d20", "d100")
    var selectedDice by remember { mutableStateOf(listOf<String>()) }
    var results by remember { mutableStateOf(listOf<Int>()) }
    var isDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Welcome to Dice Master", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)


        DiceSelector(diceTypes) { dice ->
            selectedDice = selectedDice + dice
        }

        Spacer(modifier = Modifier.height(16.dp))

        DiceDisplay(selectedDice, results)

        Spacer(modifier = Modifier.height(16.dp))

        DiceRoller(selectedDice) {
            selectedDice.map { die ->
                val maxValue = die.drop(1).toInt()
                (1..maxValue).random()
            }.also { results = it }  // Assign rolled results to the results state
        }

        Button(
            onClick = {
                // Show the dialog when the button is clicked
                isDialogVisible = true
            }
        ) {
            Text("How to roll")
        }

        if (isDialogVisible) {
            MyComposeDialog {
                // Dismiss the dialog when needed
                isDialogVisible = false

    }
}}}
