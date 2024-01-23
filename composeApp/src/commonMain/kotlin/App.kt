import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.Habit
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class, ExperimentalMaterialApi::class)
@Composable
fun App(appModule: AppModule) {
    val dataSource = appModule.provideDataSource()
    var habits by remember {
        mutableStateOf(emptyList<Habit>())
    }
    val daysOfWeek = listOf("Dom.", "Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.")

    fun getHabits() {
        try {
            GlobalScope.launch {
                dataSource.getAll().collect { h ->
                    habits = h
                }
            }
        } catch(e: Exception) {

        }
    }

    fun createHabit() {
        GlobalScope.launch {
            dataSource.insert("habit${Random.nextInt(1, 101)}")
            getHabits()
        }
    }


//    LaunchedEffect(true) {
//        dataSource.deleteAll()
//    }

    MaterialTheme {

        Scaffold(topBar = {
            TopAppBar(title = { Text("Olá, Thiago!") }, actions = {
                IconButton(onClick = {
                    createHabit()
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar hábito")
                }
            })
        }) {
            Column(Modifier.padding(it)) {
                LazyColumn(
                    Modifier.padding(horizontal = 10.dp)
                ) {
                    items(habits) { habit ->
                        Card(
                            Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            elevation = 0.dp,
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        habit.createdAt.toString(),
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f, false)
                                    )
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = "Categoria do hábito",
                                        tint = MaterialTheme.colors.primary
                                    )
                                }
                                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    items(daysOfWeek) { day ->
                                        //                                        if (habit.daysDone.contains(day)) {
                                        //                                            Button(onClick = {}) {
                                        //                                                Text(day)
                                        //                                            }
                                        //                                        } else {
                                        //                                            OutlinedButton(onClick = {}) {
                                        //                                                Text(day)
                                        //                                            }
                                        //                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}