package org.glenchiridza.task_application.service

import org.glenchiridza.task_application.TaskRepository.TaskRepository
import org.glenchiridza.task_application.dto.TaskDto
import org.glenchiridza.task_application.models.Task

class TaskService(private val taskRepository: TaskRepository) {

    private fun convertEntityToDto(task: Task):TaskDto{
        return TaskDto(
            task.id,
            task.description,
            task.isReminderOn,
            task.isTaskPending,
            task.createdDate,
            task.priority
        )
    }

    private fun assignValue

}