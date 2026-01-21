//package np.com.bimalkafle.realtimeweather.goalscreen
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CreateGoalScreen(
//    viewModel: SavingsGoalViewModel,
//    onNavigateBack: () -> Unit,
//    onGoalCreated: () -> Unit
//) {
//    var goalName by remember { mutableStateOf("") }
//    var selectedCategory by remember { mutableStateOf("Travelling") }
//    var targetAmount by remember { mutableStateOf("") }
//    var targetDate by remember { mutableStateOf<Date?>(null) }
//    var showDatePicker by remember { mutableStateOf(false) }
//    var expandedCategory by remember { mutableStateOf(false) }
//
//    val showSuccess by viewModel.showSuccessDialog.collectAsState()
//
//    LaunchedEffect(showSuccess) {
//        if (showSuccess != null) {
//            kotlinx.coroutines.delay(100)
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Create a Goal", color = Color.White) },
//                navigationIcon = {
//                    IconButton(onClick = onNavigateBack) {
//                        Text("←", color = Color.White, fontSize = 24.sp)
//                    }
//                },
//                actions = {
//                    IconButton(onClick = onNavigateBack) {
//                        Text("✕", color = Color.White, fontSize = 20.sp)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF1B5E20)
//                )
//            )
//        }
//    ) { padding ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                Text(
//                    "Please let's have the following:",
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
//
//                // Goal Name
//                Column {
//                    Text(
//                        "Goal Name",
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                    OutlinedTextField(
//                        value = goalName,
//                        onValueChange = { goalName = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text("e.g., Dubai Trip") },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFF4CAF50),
//                            unfocusedBorderColor = Color.LightGray
//                        )
//                    )
//                }
//
//                // Goal Category
//                Column {
//                    Text(
//                        "Goal Category",
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                    ExposedDropdownMenuBox(
//                        expanded = expandedCategory,
//                        onExpandedChange = { expandedCategory = it }
//                    ) {
//                        OutlinedTextField(
//                            value = selectedCategory,
//                            onValueChange = {},
//                            readOnly = true,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .menuAnchor(),
//                            trailingIcon = {
//                                Text("▼", fontSize = 12.sp)
//                            },
//                            shape = RoundedCornerShape(8.dp),
//                            colors = OutlinedTextFieldDefaults.colors(
//                                focusedBorderColor = Color(0xFF4CAF50),
//                                unfocusedBorderColor = Color.LightGray
//                            )
//                        )
//                        ExposedDropdownMenu(
//                            expanded = expandedCategory,
//                            onDismissRequest = { expandedCategory = false }
//                        ) {
//                            viewModel.categories.forEach { category ->
//                                DropdownMenuItem(
//                                    text = { Text(category) },
//                                    onClick = {
//                                        selectedCategory = category
//                                        expandedCategory = false
//                                    }
//                                )
//                            }
//                        }
//                    }
//                }
//
//                // Target Amount
//                Column {
//                    Text(
//                        "Target Amount",
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                    OutlinedTextField(
//                        value = targetAmount,
//                        onValueChange = {
//                            if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*$"))) {
//                                targetAmount = it
//                            }
//                        },
//                        modifier = Modifier.fillMaxWidth(),
//                        leadingIcon = {
//                            Text("KES", color = Color.Gray, fontSize = 14.sp)
//                        },
//                        placeholder = { Text("10,000.00") },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFF4CAF50),
//                            unfocusedBorderColor = Color.LightGray
//                        )
//                    )
//                }
//
//                // Target Date
//                Column {
//                    Text(
//                        "Savings Target Date",
//                        fontSize = 12.sp,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(bottom = 4.dp)
//                    )
//                    OutlinedTextField(
//                        value = targetDate?.let { formatDate(it) } ?: "",
//                        onValueChange = {},
//                        readOnly = true,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { showDatePicker = true },
//                        placeholder = { Text("24/08/2026") },
//                        trailingIcon = {
//                            Text("📅", fontSize = 20.sp)
//                        },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFF4CAF50),
//                            unfocusedBorderColor = Color.LightGray
//                        )
//                    )
//                }
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                Button(
//                    onClick = {
//                        if (goalName.isNotEmpty() && targetAmount.isNotEmpty()) {
//                            viewModel.createGoal(
//                                name = goalName,
//                                category = selectedCategory,
//                                targetAmount = targetAmount.toDoubleOrNull() ?: 0.0,
//                                targetDate = targetDate
//                            )
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF4CAF50)
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    enabled = goalName.isNotEmpty() && targetAmount.isNotEmpty()
//                ) {
//                    Text("Create Goal", fontSize = 16.sp)
//                }
//            }
//
//            // Success Dialog
//            showSuccess?.let { title ->
//                SuccessDialog(
//                    title = title,
//                    onDismiss = {
//                        viewModel.dismissSuccessDialog()
//                        onGoalCreated()
//                    }
//                )
//            }
//        }
//    }
//
//    // Simple Date Picker (you can use a proper date picker library)
//    if (showDatePicker) {
//        AlertDialog(
//            onDismissRequest = { showDatePicker = false },
//            title = { Text("Select Target Date") },
//            text = {
//                Column {
//                    Text("Use a date picker library like compose-material3 DatePicker")
//                    Text("For now, setting date to 1 year from now")
//                }
//            },
//            confirmButton = {
//                Button(onClick = {
//                    val calendar = Calendar.getInstance()
//                    calendar.add(Calendar.YEAR, 1)
//                    targetDate = calendar.time
//                    showDatePicker = false
//                }) {
//                    Text("OK")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { showDatePicker = false }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}