package org.glenchiridza.task_application.dto.requests

import org.glenchiridza.task_application.enums.Priority

data class TaskUpdateRequest(
    val description:String?,
    val isReminderOn:Boolean?,
    val isTaskPending:Boolean?,
    val priority: Priority
)