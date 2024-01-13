package org.glenchiridza.task_application.dto

import org.glenchiridza.task_application.enums.Priority
import java.time.LocalDateTime

data class TaskDto(
    val id:Long,
    val description:String,
    val isReminderOn:Boolean,
    val isTaskPending:Boolean,
    val createdDate:LocalDateTime,
    val priority: Priority
)