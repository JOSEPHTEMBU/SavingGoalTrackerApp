package np.com.bimalkafle.realtimeweather.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.Entity.TransactionEntity

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals ORDER BY id DESC")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE id = :id")
    suspend fun getGoalById(id: Long): GoalEntity?

    @Insert
    suspend fun insertGoal(goal: GoalEntity): Long

    @Update
    suspend fun updateGoal(goal: GoalEntity)

    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE goalId = :goalId ORDER BY date DESC")
    fun getTransactionsForGoal(goalId: Long): Flow<List<TransactionEntity>>
}