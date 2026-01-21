package np.com.bimalkafle.realtimeweather.viewmodel


//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
//import np.com.bimalkafle.realtimeweather.Repository.GoalRepository
//import java.util.Date
//
//class GoalViewModel(private val repository: GoalRepository) : ViewModel() {
//
//    val goals = repository.allGoals
//    var showSuccessMessage = mutableStateOf<String?>(null)
//    var showCreateDialog = mutableStateOf(false)
//    var selectedGoal = mutableStateOf<GoalEntity?>(null)
//
//    fun createGoal(name: String, category: String, targetAmount: Double, targetDate: Date?) {
//        viewModelScope.launch {
//            val goal = GoalEntity(
//                name = name,
//                category = category,
//                targetAmount = targetAmount,
//                targetDate = targetDate?.time
//            )
//            repository.insertGoal(goal)
//        }
//    }
//
//    fun depositToGoal(goalId: Long, amount: Double) {
//        viewModelScope.launch {
//            val goal = repository.getGoalById(goalId) ?: return@launch
//            val newAmount = goal.currentAmount + amount
//            repository.updateGoal(goal.copy(currentAmount = newAmount))
//            repository.addTransaction(goalId, amount, "deposit")
//        }
//    }
//
//    fun withdrawFromGoal(goalId: Long, amount: Double) {
//        viewModelScope.launch {
//            val goal = repository.getGoalById(goalId) ?: return@launch
//            val newAmount = (goal.currentAmount - amount).coerceAtLeast(0.0)
//            repository.updateGoal(goal.copy(currentAmount = newAmount))
//            repository.addTransaction(goalId, amount, "withdraw")
//        }
//    }
//}


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.Entity.TransactionEntity // ← Add this import
import np.com.bimalkafle.realtimeweather.Repository.GoalRepository
import java.util.Date

class GoalViewModel(private val repository: GoalRepository) : ViewModel() {

    val goals = repository.allGoals

    var showSuccessMessage = mutableStateOf<String?>(null)
    var showCreateDialog = mutableStateOf(false)
    var selectedGoal = mutableStateOf<GoalEntity?>(null)

    // ←←← THIS IS THE MISSING FUNCTION YOU NEED ←←←
    fun getTransactions(goalId: Long): Flow<List<TransactionEntity>> {
        return repository.getTransactions(goalId)
    }
    // ←←← END ←←←

    fun createGoal(name: String, category: String, targetAmount: Double, targetDate: Date?) {
        viewModelScope.launch {
            val goal = GoalEntity(
                name = name,
                category = category,
                targetAmount = targetAmount,
                targetDate = targetDate?.time
            )
            repository.insertGoal(goal)
            // Optional: show success message after creation
            showSuccessMessage.value = "$name Goal\nCreated Successfully"
        }
    }

    fun depositToGoal(goalId: Long, amount: Double) {
        viewModelScope.launch {
            val goal = repository.getGoalById(goalId) ?: return@launch
            val newAmount = goal.currentAmount + amount
            repository.updateGoal(goal.copy(currentAmount = newAmount))
            repository.addTransaction(goalId, amount, "deposit")
            // Optional: success feedback
            showSuccessMessage.value = "${String.format("%,.0f", amount)} KES\nDeposit Successful!"
        }
    }

    fun withdrawFromGoal(goalId: Long, amount: Double) {
        viewModelScope.launch {
            val goal = repository.getGoalById(goalId) ?: return@launch
            val newAmount = (goal.currentAmount - amount).coerceAtLeast(0.0)
            repository.updateGoal(goal.copy(currentAmount = newAmount))
            repository.addTransaction(goalId, amount, "withdraw")
            // Optional: success feedback
            showSuccessMessage.value = "${String.format("%,.0f", amount)} KES\nWithdraw Successful!"
        }
    }
}