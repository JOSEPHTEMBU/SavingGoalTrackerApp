package np.com.bimalkafle.realtimeweather


//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import np.com.bimalkafle.realtimeweather.viewmodel.GoalViewModel
//
//import java.util.*
//
//@Composable
//fun CreateGoalDialog(viewModel: GoalViewModel, onDismiss: () -> Unit) {
//    var name by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("General") }
//    var targetAmount by remember { mutableStateOf("") }
//    var targetDate by remember { mutableStateOf<Date?>(null) }
//
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = { Text("Create a Goal", color = Color.White) },
//        containerColor = Color(0xFF1E6B3F),
//        text = {
//            Column {
//                OutlinedTextField(
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text("Goal Name") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                var expanded by remember { mutableStateOf(false) }
//                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
//                    OutlinedTextField(
//                        value = category,
//                        onValueChange = {},
//                        readOnly = true,
//                        label = { Text("Goal Category") },
//                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                        modifier = Modifier.menuAnchor().fillMaxWidth()
//                    )
//                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//                        listOf("General", "Traveling", "Family", "Education", "Emergency").forEach {
//                            DropdownMenuItem(text = { Text(it) }, onClick = {
//                                category = it
//                                expanded = false
//                            })
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = targetAmount,
//                    onValueChange = { if (it.toDoubleOrNull() != null || it.isEmpty()) targetAmount = it },
//                    label = { Text("Target Amount (KES)") },
//                    prefix = { Text("KES ") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                DatePickerField(
//                    label = "Savings Target Date (Optional)",
//                    selectedDate = targetDate,
//                    onDateSelected = { targetDate = it }
//                )
//            }
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    val amount = targetAmount.toDoubleOrNull() ?: return@TextButton
//                    viewModel.createGoal(name, category, amount, targetDate)
//                    onDismiss()
//                    // Show success popup
//                    viewModel.showSuccessMessage.value = "$name Goal\nCreated Successfully"
//                },
//                enabled = name.isNotBlank() && targetAmount.toDoubleOrNull() != null
//            ) {
//                Text("Create", color = Color.White)
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismiss) {
//                Text("Cancel", color = Color.White)
//            }
//        }
//    )
//}


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import np.com.bimalkafle.realtimeweather.viewmodel.GoalViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class) // ← This removes the experimental warning
@Composable
fun CreateGoalDialog(
    viewModel: GoalViewModel,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("General") }
    var targetAmount by remember { mutableStateOf("") }
    var targetDate by remember { mutableStateOf<Date?>(null) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Create a Goal", color = Color.White)
        },
        containerColor = Color(0xFF1E6B3F),
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Goal Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Goal Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("General", "Traveling", "Family", "Education", "Emergency").forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    category = item
                                    expanded = false // ← Fixed: was missing assignment
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = targetAmount,
                    onValueChange = { newValue ->
                        if (newValue.toDoubleOrNull() != null || newValue.isEmpty()) {
                            targetAmount = newValue
                        }
                    },
                    label = { Text("Target Amount (KES)") },
                    prefix = { Text("KES ") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                DatePickerField(
                    label = "Savings Target Date (Optional)",
                    selectedDate = targetDate,
                    onDateSelected = { targetDate = it }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val amount = targetAmount.toDoubleOrNull() ?: return@TextButton
                    if (name.isBlank()) return@TextButton

                    viewModel.createGoal(name, category, amount, targetDate)
                    viewModel.showSuccessMessage.value = "$name Goal\nCreated Successfully"
                    onDismiss()
                },
                enabled = name.isNotBlank() && targetAmount.toDoubleOrNull() != null
            ) {
                Text("Create", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White)
            }
        }
    )
}