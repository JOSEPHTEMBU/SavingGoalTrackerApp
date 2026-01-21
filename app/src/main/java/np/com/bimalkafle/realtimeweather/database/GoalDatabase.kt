package np.com.bimalkafle.realtimeweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import np.com.bimalkafle.realtimeweather.Entity.GoalEntity
import np.com.bimalkafle.realtimeweather.Entity.TransactionEntity
import np.com.bimalkafle.realtimeweather.dao.GoalDao

@Database(entities = [GoalEntity::class, TransactionEntity::class], version = 1, exportSchema = false)
abstract class GoalDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao

    companion object {
        @Volatile
        private var INSTANCE: GoalDatabase? = null

        fun getDatabase(context: Context): GoalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalDatabase::class.java,
                    "goal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}