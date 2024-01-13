package org.glenchiridza.task_application.service

import org.glenchiridza.task_application.TaskRepository.TaskRepository
import org.glenchiridza.task_application.dto.TaskDto
import org.glenchiridza.task_application.dto.requests.TaskCreateRequest
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

    private fun assignValuesToEntity(task:Task,taskRequest:TaskCreateRequest){
        task.description = taskRequest.description
        task.isReminderOn = taskRequest.isReminderOn
        task.isTaskPending = taskRequest.isTaskPending
        task.createdDate = taskRequest.createdDate
        task.priority = taskRequest.priority
    }

    private fun verifyTaskId(id:Long){
        if(!taskRepository.existsById(id)){
            throw TaskNotFoundException("task with specified id : $id not found")
        }
    }

}