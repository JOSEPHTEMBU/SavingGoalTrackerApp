package np.com.bimalkafle.realtimeweather


import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun DatePickerField(
    label: String,
    selectedDate: Date?,
    onDateSelected: (Date?) -> Unit
) {
    val context = LocalContext.current
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    OutlinedTextField(
        value = selectedDate?.let { formatter.format(it) } ?: "",
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.time = it }
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val newDate = Calendar.getInstance().apply {
                            set(year, month, day)
                        }.time
                        onDateSelected(newDate)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text("📅")
            }
        },
        modifier = androidx.compose.ui.Modifier.fillMaxWidth()
    )
}