package org.glenchiridza.task_application.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.glenchiridza.task_application.enums.Priority
import java.time.LocalDateTime


@Entity
@Table(name = "task", uniqueConstraints = [UniqueConstraint(name = "uk_task_description", columnNames = ["description"])])
class Task {

    @Id
    @GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    val id:Long =0

    @NotBlank
    @Column(name = "description", nullable = false, unique = true)
    lateinit var description:String

    @Column(name = "is_reminder_on", nullable = false)
    var isReminderOn:Boolean = false;

    @Column(name = "is_task_pending", nullable = false)
    var isTaskPending:Boolean = true

    @Column(name = "created_date", nullable = false)
    var createdDate:LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_date", nullable = false)
    var updatedDate:LocalDateTime = LocalDateTime.now()

    @NotNull
    @Enumerated(EnumType.STRING)
    var priority: Priority = Priority.LOW





}