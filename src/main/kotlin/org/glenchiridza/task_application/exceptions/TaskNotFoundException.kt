package org.glenchiridza.task_application.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
data class TaskNotFoundException(
    override val message:String
):RuntimeException()