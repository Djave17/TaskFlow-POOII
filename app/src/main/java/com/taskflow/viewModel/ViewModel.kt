package com.taskflow.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.taskflow.repository.TareaRepository


class TareaViewModel: ViewModel() {
    private val repository = TareaRepository()

    var tasks by mutableStateOf(repository.getTareas())
        private set

    var id by mutableStateOf("")
        private set
    var titulo by mutableStateOf("")
        private set
    var completado by mutableStateOf(false)
    var prioridad by mutableStateOf("Media")


    fun onIdChange(newId: String) {
        this.id = newId
    }

    fun onTitleChange(newTitle: String) {
        this.titulo = newTitle
    }

    fun onCompletedChange(newCompleted: Boolean) {
        this.completado = newCompleted
    }

    fun onProrityChange(newPriority: String) {
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
            completado = it.completado
            prioridad = prioridadIntToString(it.prioridad)
        } ?: clearForm()
    }


    private fun prioridadIntToString(p: Int): String = when (p) {
        1 -> "Baja"
        2 -> "Media"
        3 -> "Alta"
        4 -> "Urgente"
        else -> "Media"
    }




    private fun clearForm(){
        id=""
        titulo=""
        completado=false
        prioridad = "Media"
    }
}
