import com.example.Database
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

class DataSource(db: Database) {
    private val queries = db.habitQueries

    fun insert(name: String) {
        queries.insertHabit(id = null, name = name, createdAt = Clock.System.now().toEpochMilliseconds())
    }

    fun getAll() = flow {
        val habits = queries.getHabits().executeAsList()
        emit(habits)
    }
}