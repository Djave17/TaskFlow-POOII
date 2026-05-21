package com.taskflow.navigation

import kotlinx.serialization.Serializable

@Serializable

object TaskList
@Serializable
data class TaskDetail(
    val taskId: Int
)

@Serializable
object Splash
