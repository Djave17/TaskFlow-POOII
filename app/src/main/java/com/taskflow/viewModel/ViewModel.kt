package com.taskflow.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.taskflow.model.Tarea
import com.taskflow.repository.TareaRepository


class TareaViewModel: ViewModel() {
    private val repository = TareaRepository()

    var tasks by mutableStateOf(repository.getTareas())
        private set

    var id by mutableStateOf("")
        private set
    var titulo by mutableStateOf("")
        private set
    var descripcion by mutableStateOf("")
        private set
    var completado by mutableStateOf(false)
    var prioridad by mutableStateOf("Media")


    fun onIdChange(newId: String) {
        this.id = newId
    }

    fun onTitleChange(newTitle: String) {
        this.titulo = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        this.descripcion = newDescription
    }

    fun onCompletedChange(newCompleted: Boolean) {
        this.completado = newCompleted
    }

    fun onPriorityChange(newPriority: String) {
        this.prioridad = newPriority
    }

    fun loadTareas() {
        tasks = repository.getTareas()
    }

    fun loadTarea(taskId: Int?) {
        if (taskId == null) {
            clearForm()
            return
        }

        val task = repository.getTareaById(taskId)
        task?.let {
            id = it.id.toString()
            titulo = it.titulo
            descripcion = it.descripcion
            completado = it.completado
            prioridad = prioridadIntToString(it.prioridad)
        } ?: clearForm()
    }

    fun saveTarea() {
        val tareaId = id.toIntOrNull() ?: return

        repository.addTarea(
            Tarea(
                id = tareaId,
                titulo = titulo,
                descripcion = descripcion,
                completado = completado,
                prioridad = prioridadStringToInt(prioridad)
            )
        )
        loadTareas()
    }

    fun deleteTarea(tarea: Tarea) {
        repository.deleteTarea(tarea)
        loadTareas()
    }

    fun toggleTarea(tarea: Tarea) {
        repository.toggleTarea(tarea)
        loadTareas()
    }


    private fun prioridadIntToString(p: Int): String = when (p) {
        1 -> "Baja"
        2 -> "Media"
        3 -> "Alta"
        4 -> "Urgente"
        else -> "Media"
    }

    private fun prioridadStringToInt(p: String): Int = when (p.trim().lowercase()) {
        "baja" -> 1
        "media" -> 2
        "alta" -> 3
        "urgente" -> 4
        else -> 2
    }



    private fun clearForm(){
        id=""
        titulo=""
        descripcion=""
        completado=false
        prioridad = "Media"
    }
}
