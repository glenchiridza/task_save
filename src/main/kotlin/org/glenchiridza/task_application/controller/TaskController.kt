package org.glenchiridza.task_application.controller

import jakarta.validation.Valid
import org.glenchiridza.task_application.dto.TaskDto
import org.glenchiridza.task_application.dto.requests.TaskCreateRequest
import org.glenchiridza.task_application.dto.requests.TaskUpdateRequest
import org.glenchiridza.task_application.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")
@CrossOrigin("*")
class TaskController(private val service: TaskService) {

    @GetMapping("all-tasks")
    fun getAllTasks():ResponseEntity<List<TaskDto>> =
        ResponseEntity<List<TaskDto>>(service.getAllTasks(),HttpStatus.OK)

    @GetMapping("pending-tasks")
    fun getAllPendingTasks():ResponseEntity<List<TaskDto>> =
        ResponseEntity<List<TaskDto>>(service.getAllPendingTasks(),HttpStatus.OK)


    @GetMapping("closed-tasks")
    fun getAllClosedTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity(service.getAllClosedTasks(),HttpStatus.OK)

    @GetMapping("task/{id}")
    fun getTaskById(@PathVariable id:Long):ResponseEntity<TaskDto> =
        ResponseEntity(service.getTaskById(id),HttpStatus.OK)

    @PostMapping("create-task")
    fun createTask(
        @Valid @RequestBody createRequest: TaskCreateRequest
    ):ResponseEntity<TaskDto> =
        ResponseEntity(service.createTask(createRequest),HttpStatus.OK)

    @PatchMapping("update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: TaskUpdateRequest
    ):ResponseEntity<TaskDto> =
        ResponseEntity(service.updateTask(id,updateRequest),HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteTask(
        @PathVariable id: Long
    ): ResponseEntity<String> =
        ResponseEntity(service.deleteTask(id),HttpStatus.OK)
}
