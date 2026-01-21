package np.com.bimalkafle.realtimeweather
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.Entity.TransactionEntity
import np.com.bimalkafle.realtimeweather.viewmodel.GoalViewModel
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalDetailScreen(
    goal: GoalEntity,
    viewModel: GoalViewModel,
    onDismiss: () -> Unit
) {
    // Fixed: Explicit type for collectAsState
    val transactions by viewModel.getTransactions(goal.id)
        .collectAsState(initial = emptyList<TransactionEntity>())

    var showDepositDialog by remember { mutableStateOf(false) }
    var showWithdrawDialog by remember { mutableStateOf(false) }

    // Fixed: Proper Double to Float conversion with safety
    val progress: Float = if (goal.targetAmount > 0) {
        (goal.currentAmount / goal.targetAmount).coerceIn(0.0, 1.0).toFloat()
    } else {
        0f
    }

    val isCompleted = progress >= 1f

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(goal.name, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E6B3F))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "KSh ${String.format("%,.0f", goal.currentAmount)}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Target: KSh ${String.format("%,.0f", goal.targetAmount)}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    LinearProgressIndicator(
                        progress = progress,  // ← Just pass the Float value directly
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp),
                        color = if (isCompleted) Color.Yellow else Color(0xFF8BC34A),
                        trackColor = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    if (isCompleted) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Goal Completed! 🎉",
                            color = Color.Yellow,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { showDepositDialog = true },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("↑ Deposit", color = Color.White)
                }

                Button(
                    onClick = { showWithdrawDialog = true },
                    modifier = Modifier.weight(1f),
                    enabled = goal.currentAmount > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                ) {
                    Text("↓ Withdraw", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Transaction History",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (transactions.isEmpty()) {
                Text("No transactions yet.", color = Color.Gray)
            } else {
                LazyColumn {
                    items(transactions) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }

    // Deposit Dialog
    if (showDepositDialog) {
        TransactionDialog(
            title = "Deposit to ${goal.name}",
            available = Double.MAX_VALUE, // No limit for deposits
            onConfirm = { amount ->
                viewModel.depositToGoal(goal.id, amount)
                viewModel.showSuccessMessage.value = "${String.format("%,.0f", amount)} KES\nDeposit Successful!"
                showDepositDialog = false
            },
            onDismiss = { showDepositDialog = false }
        )
    }

    // Withdraw Dialog
    if (showWithdrawDialog) {
        TransactionDialog(
            title = "Withdraw from ${goal.name}",
            available = goal.currentAmount,
            onConfirm = { amount ->
                viewModel.withdrawFromGoal(goal.id, amount)
                viewModel.showSuccessMessage.value = "${String.format("%,.0f", amount)} KES\nWithdraw Successful!"
                showWithdrawDialog = false
            },
            onDismiss = { showWithdrawDialog = false }
        )
    }
}

@Composable
fun TransactionItem(transaction: TransactionEntity) {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = Date(transaction.date)
    val sign = if (transaction.type == "deposit") "+" else "-"
    val color = if (transaction.type == "deposit") Color(0xFF4CAF50) else Color(0xFFE91E63)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (transaction.type == "deposit") "Deposit" else "Withdrawal",
            color = color,
            fontWeight = FontWeight.Medium
        )

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$sign KES ${String.format("%,.0f", transaction.amount)}",
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = formatter.format(date),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}