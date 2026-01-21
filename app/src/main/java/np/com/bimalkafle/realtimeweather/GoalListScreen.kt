package np.com.bimalkafle.realtimeweather


import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.viewmodel.GoalViewModel
import java.text.StringCharacterIterator
import java.util.*

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun GoalListScreen(viewModel: GoalViewModel) {
//    val goals by viewModel.goals.collectAsState(initial = emptyList())
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("My Goals", color = Color.White) },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E6B3F))
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { viewModel.showCreateDialog.value = true },
//                containerColor = Color(0xFF4CAF50)
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Goal", tint = Color.White)
//            }
//        }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding)) {
//            if (goals.isEmpty()) {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text(
//                        "No goals yet. Create one!",
//                        style = MaterialTheme.typography.headlineMedium
//                    )
//                }
//            } else {
//                LazyColumn {
//                    items(goals) { goal ->
//                        GoalCard(
//                            goal = goal,
//                            onClick = { viewModel.selectedGoal.value = goal }
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    // Create Goal Dialog
//    if (viewModel.showCreateDialog.value) {
//        CreateGoalDialog(
//            viewModel = viewModel,
//            onDismiss = { viewModel.showCreateDialog.value = false }
//        )
//    }
//
//    // Goal Detail Screen
//    viewModel.selectedGoal.value?.let { goal ->
//        GoalDetailScreen(
//            goal = goal,
//            viewModel = viewModel,
//            onDismiss = { viewModel.selectedGoal.value = null }
//        )
//    }
//
//    // ←←← SUCCESS POPUP – Correctly placed at screen level ←←←
//    viewModel.showSuccessMessage.value?.let { message ->
//        SuccessPopup(
//            message = message,
//            onDismiss = { viewModel.showSuccessMessage.value = null }
//        )
//    }
//}
//
//@Composable
//fun GoalCard(goal: GoalEntity, onClick: () -> Unit) {
//    val progress = if (goal.targetAmount > 0) {
//        (goal.currentAmount / goal.targetAmount).coerceIn(0.0, 1.0).toFloat()
//    } else {
//        0f
//    }
//    val isCompleted = progress >= 1f
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(12.dp)
//            .clickable { onClick() },
//        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    goal.name,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    style = MaterialTheme.typography.titleLarge
//                )
//                if (isCompleted) {
//                    Text("Completed!", color = Color.Yellow, fontWeight = FontWeight.Bold)
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                "KSh ${String.format("%,.2f", goal.currentAmount)} / KSh ${String.format("%,.2f", goal.targetAmount)}",
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            LinearProgressIndicator(
//                progress = progress,
//                modifier = Modifier.fillMaxWidth(),
//                color = if (isCompleted) Color.Yellow else Color(0xFF8BC34A)
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text("${(progress * 100).toInt()}%", color = Color.White)
//        }
//    }
//}



///////


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.filled.Person


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalListScreen(viewModel: GoalViewModel) {
    val goals by viewModel.goals.collectAsState(initial = emptyList())

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // ←←← 1. GREETING HEADER – Full top green banner ←←←
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E6B3F))
                    .padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User",
                        tint = Color.White,
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color(0xFF2E7D32), shape = CircleShape)
                            .padding(14.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            "Hello There!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "It's a good day to save",
                            color = Color.White.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            // ←←← 2. "My Goals" + "+ Add a Goal" – Now BELOW greeting ←←←
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "My Goals",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                TextButton(
                    onClick = { viewModel.showCreateDialog.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Goal",
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        "Add a Goal",
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // ←←← 3. Goal List or Empty State ←←←
            if (goals.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No goals yet. Create one!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(goals) { goal ->
                        GoalCard(
                            goal = goal,
                            onClick = { viewModel.selectedGoal.value = goal }
                        )
                    }
                }
            }
        }
    }

    // Dialogs and Popups
    if (viewModel.showCreateDialog.value) {
        CreateGoalDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.showCreateDialog.value = false }
        )
    }

    viewModel.selectedGoal.value?.let { goal ->
        GoalDetailScreen(
            goal = goal,
            viewModel = viewModel,
            onDismiss = { viewModel.selectedGoal.value = null }
        )
    }

    viewModel.showSuccessMessage.value?.let { message ->
        SuccessPopup(
            message = message,
            onDismiss = { viewModel.showSuccessMessage.value = null }
        )
    }
}

@Composable
fun GoalCard(goal: GoalEntity, onClick: () -> Unit) {
    val progress = if (goal.targetAmount > 0) {
        (goal.currentAmount / goal.targetAmount).coerceIn(0.0, 1.0).toFloat()
    } else {
        0f
    }
    val isCompleted = progress >= 1f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    goal.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                if (isCompleted) {
                    Text("Completed!", color = Color.Yellow, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "KSh ${String.format("%,.2f", goal.currentAmount)} / KSh ${String.format("%,.2f", goal.targetAmount)}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = if (isCompleted) Color.Yellow else Color(0xFF8BC34A),
                trackColor = Color(0xFF1B5E20)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "${(progress * 100).toInt()}%",
                color = Color.White,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}