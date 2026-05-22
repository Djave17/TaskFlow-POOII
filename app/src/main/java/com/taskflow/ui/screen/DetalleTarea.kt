package com.taskflow.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.taskflow.viewModel.TareaViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: Int,
    viewModel: TareaViewModel = viewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.loadTarea(taskId.takeIf { it != -1 })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = if (taskId == -1) "Nueva tarea" else "Editar tarea",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = viewModel.id,
                    onValueChange = { viewModel.onIdChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("ID") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    },
                    singleLine = true,
                    enabled = taskId == -1
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = viewModel.titulo,
                    onValueChange = { viewModel.onTitleChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Titulo") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = viewModel.descripcion,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Descripcion") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = viewModel.prioridad,
                    onValueChange = { viewModel.onPriorityChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Prioridad (Baja/Media/Alta/Urgente)") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cancelar")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (viewModel.id.toIntOrNull() != null && viewModel.titulo.isNotBlank()) {
                                viewModel.saveTarea()
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}
