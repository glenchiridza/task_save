package org.glenchiridza.task_application.TaskRepository

import org.glenchiridza.task_application.models.Task
import org.springframework.context.annotation.Description
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


interface TaskRepository :JpaRepository<Task,Long> {

    fun findTaskById(id:Long):Task

    @Query(value = "SELECT * FROM task WHERE is_task_pending = TRUE", nativeQuery = true)
    fun getAllPendingTasks():List<Task>

    @Query("SELECT * FROM task WHERE is_task_pending = FALSE", nativeQuery = true)
    fun getAllClosedTasks():List<Task>

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM Task t WHERE t.description = ?1")
    fun descriptionExists(description: String):Boolean

}