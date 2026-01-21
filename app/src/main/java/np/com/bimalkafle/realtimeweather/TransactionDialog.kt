package np.com.bimalkafle.realtimeweather


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TransactionDialog(
    title: String,
    available: Double = Double.MAX_VALUE,
    onConfirm: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    var amountText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, color = Color.White) },
        containerColor = Color(0xFF1E6B3F),
        text = {
            Column {
                Text("Available balance: KES ${String.format("%,.0f", available)}", color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = amountText,
                    onValueChange = {
                        if (it.toDoubleOrNull() != null || it.isEmpty()) amountText = it
                    },
                    label = { Text("Amount to transact") },
                    prefix = { Text("KES ") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val amount = amountText.toDoubleOrNull() ?: return@TextButton
                    if (amount > 0 && amount <= available) {
                        onConfirm(amount)
                    }
                }
            ) {
                Text("Confirm", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White)
            }
        }
    )
}