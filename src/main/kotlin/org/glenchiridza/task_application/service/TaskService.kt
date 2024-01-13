package org.glenchiridza.task_application.service

import org.glenchiridza.task_application.TaskRepository.TaskRepository
import org.glenchiridza.task_application.dto.TaskDto
import org.glenchiridza.task_application.dto.requests.TaskCreateRequest
import org.glenchiridza.task_application.dto.requests.TaskUpdateRequest
import org.glenchiridza.task_application.exceptions.BadRequestException
import org.glenchiridza.task_application.exceptions.TaskNotFoundException
import org.glenchiridza.task_application.models.Task
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.Collections
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
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

    fun getAllTasks():List<TaskDto> =
        taskRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getAllPendingTasks():List<TaskDto> =
        taskRepository.getAllPendingTasks().stream().map { t -> convertEntityToDto(t) }.collect(Collectors.toList())

    fun getAllClosedTasks():List<TaskDto> =
        taskRepository.getAllClosedTasks().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getTaskById(id:Long):TaskDto {
        verifyTaskId(id)
        val task:Task = taskRepository.findTaskById(id)
        return convertEntityToDto(task)
    }

    fun createTask(createRequest: TaskCreateRequest):TaskDto{
        if(taskRepository.descriptionExists(createRequest.description))
            throw BadRequestException("The task with given description already exists")
        val task = Task()
        assignValuesToEntity(task,createRequest)
        val saveTask = taskRepository.save(task)
        return convertEntityToDto(saveTask)
    }

    fun updateTask(id: Long,updateRequest: TaskUpdateRequest):TaskDto{
        verifyTaskId(id)

        val existingTask:Task = taskRepository.findTaskById(id)
        for (prop in TaskUpdateRequest::class.memberProperties){
            if(prop.get(updateRequest) != null){
                val field:Field? = ReflectionUtils.findField(Task::class.java,prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it,existingTask,prop.get(updateRequest))
                }
            }
        }
        val saveTask:Task = taskRepository.save(existingTask)
        return convertEntityToDto(saveTask)
    }

    fun deleteTask(id:Long):String{
        verifyTaskId(id)

        taskRepository.deleteById(id)
        return "task at $id has been deleted!!!"
    }
}