import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class Habit(
    val name: String,
    val daysDone: List<String>
)

@Composable
fun App() {
    val habits = listOf(
        Habit("Academia", listOf("Seg.", "Ter.", "Qui.")),
        Habit("Meditação", listOf("Seg.", "Qua.", "Sex.")),
        Habit("Leitura", listOf("Ter.", "Qui.", "Sáb.")),
        Habit("Yoga", listOf("Seg.", "Qua.", "Sex.")),
        Habit("2L de água", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Planejar o dia na noite anterior", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.")),
        Habit("Praticar gratidão diariamente", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Acompanhar metas de curto e longo prazo", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.")),
        Habit("Aprender algo novo regularmente", listOf("Ter.", "Qui.", "Sáb.")),
        Habit("Manter uma dieta balanceada", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.")),
        Habit("Estabelecer horários regulares de sono", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Fazer pausas curtas durante o trabalho", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.")),
        Habit("Cultivar relacionamentos positivos", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Realizar revisões periódicas de progresso", listOf("Seg.", "Qua.", "Sex.")),
        Habit("Praticar a empatia", listOf("Seg.", "Qua.", "Sex.")),
        Habit("Limitar o tempo nas redes sociais", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.")),
        Habit("Manter um ambiente limpo e organizado", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Promover a sustentabilidade", listOf("Seg.", "Qua.", "Sex.")),
        Habit("Expressar gratidão a outras pessoas", listOf("Seg.", "Qua.", "Sex.")),
        Habit("Praticar mindfulness", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.", "Dom.")),
        Habit("Fazer uma pausa para reflexão no final do dia", listOf("Seg.", "Ter.", "Qua.", "Qui.", "Sex."))
    )
    val daysOfWeek = listOf("Dom.", "Seg.", "Ter.", "Qua.", "Qui.", "Sex.", "Sáb.")

    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Olá, Thiago!") }, actions = {
                IconButton(onClick = {}) {
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
                                        if (habit.daysDone.contains(day)) {
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