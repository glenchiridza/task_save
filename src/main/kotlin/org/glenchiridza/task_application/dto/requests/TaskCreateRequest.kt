package org.glenchiridza.task_application.dto.requests

import jakarta.validation.constraints.NotBlank
import org.glenchiridza.task_application.enums.Priority
import java.time.LocalDateTime

data class TaskCreateRequest (
    @NotBlank(message = "description can't be empty")
    val description:String,

    val isReminderOn:Boolean,

    val isTaskPending:Boolean,

    @NotBlank(message = "created date can't be empty")
    val createdDate:LocalDateTime,

    val priority: Priority
)