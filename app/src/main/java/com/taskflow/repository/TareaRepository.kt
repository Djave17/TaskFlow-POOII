package com.taskflow.repository

import com.taskflow.model.Tarea

class TareaRepository{
    private val tareas = mutableListOf(
        Tarea(1, "Comprar comida", "Comprar leche, pan y huevos", false, 3),
        Tarea(2 , "Tarea de Fisica Aplicada", "Realizar ejercicios de guia 7", false, 2),
        Tarea(3 , "Tarea de POO II", "Realizar ejercicios de guia 7", false, 1),
        Tarea(4 , "Tarea de Finanzas", "Realizar ejercicios de guia 7", false, 4),
        )

    fun getTarea(): List<Tarea> {
        return tareas
    }

    fun addTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun getTareaById(tarea: Tarea): Tarea {
        return tareas.firstOrNull { it.id == tarea.id } ?: throw IllegalArgumentException("Tarea no encontrada")
    }

    fun updateTarea(tarea: Tarea){
        val indice = tareas.indexOfFirst { it.id == tarea.id}
        if (indice != -1) tareas[indice] = tarea
    }

    fun deleteTarea(tarea: Tarea){
        tareas.remove(tarea)
    }

    fun toggleTarea(tarea: Tarea){
        val indice = tareas.indexOf(tarea)
        if (indice != -1) {
            val tareaActualizada = tareas[indice].copy(isComplete = !tareas[indice].isComplete)
            tareas[indice] = tareaActualizada
        }

    }

}