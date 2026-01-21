package np.com.bimalkafle.realtimeweather.Models

import java.util.Date

data class GoalWithProgress(
    val id: Long,
    val name: String,
    val category: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val targetDate: Date?,
    val progressPercentage: Float,
    val remainingAmount: Double,
    val isCompleted: Boolean
)

data class GoalCategory(
    val name: String,
    val icon: String
)