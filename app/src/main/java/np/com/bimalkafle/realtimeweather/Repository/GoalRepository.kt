package np.com.bimalkafle.realtimeweather.Repository
import kotlinx.coroutines.flow.Flow
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.Entity.TransactionEntity
import np.com.bimalkafle.realtimeweather.dao.GoalDao

class GoalRepository(private val goalDao: GoalDao) {
    val allGoals: Flow<List<GoalEntity>> = goalDao.getAllGoals()

    suspend fun insertGoal(goal: GoalEntity): Long = goalDao.insertGoal(goal)

    suspend fun updateGoal(goal: GoalEntity) = goalDao.updateGoal(goal)

    suspend fun getGoalById(id: Long) = goalDao.getGoalById(id)

    suspend fun addTransaction(goalId: Long, amount: Double, type: String) {
        val transaction = TransactionEntity(goalId = goalId, amount = amount, type = type)
        goalDao.insertTransaction(transaction)
    }

    fun getTransactions(goalId: Long) = goalDao.getTransactionsForGoal(goalId)
}