package com.taskflow.model

data class Tarea (
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val isComplete: Boolean = false
)