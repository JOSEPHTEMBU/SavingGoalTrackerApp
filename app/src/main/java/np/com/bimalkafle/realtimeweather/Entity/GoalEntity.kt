package np.com.bimalkafle.realtimeweather.Entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String = "General",
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val targetDate: Long? = null // timestamp
)

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val goalId: Long,
    val amount: Double,
    val date: Long = System.currentTimeMillis(),
    val type: String // "deposit" or "withdraw"
)