import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.Habit
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(appModule: AppModule) {
    val dataSource = appModule.provideDataSource()
    var habits by remember {
        mutableStateOf(emptyList<Habit>())
    }
    val daysOfWeek = listOf("Dom.", "Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.")

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    var habitName by remember {
        mutableStateOf("")
    }

    val suggestions = listOf(
        "Beber mais água",
        "Caminhar 10 minutos por dia",
        "Alongar pela manhã",
        "Respirar profundamente",
        "Evitar alimentos processados",
        "Desconectar antes de dormir",
        "Agradecer diariamente",
        "Planejar o dia",
        "Arrumar a cama todas as manhãs",
        "Ler 10 minutos por dia"
    )

    MaterialTheme {
        ModalBottomSheetLayout({
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "✨ Novo hábito ✨",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    habitName,
                    { habitName = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(suggestions.random()) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )

                Button({ scope.launch { state.hide() } }, modifier = Modifier.fillMaxWidth()) {
                    Text("Salvar")
                }
            }
        }, sheetState = state) {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Olá, Thiago!") }, actions = {
                    IconButton(onClick = { scope.launch { state.show() } }) {
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
                                            habit.name,
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
                                            if (Random.nextBoolean()) {
                                                Button(onClick = {}) {
                                                    Text(day)
                                                }
                                            } else {
                                                OutlinedButton(onClick = {}) {
                                                    Text(day)
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
        }
    }
}