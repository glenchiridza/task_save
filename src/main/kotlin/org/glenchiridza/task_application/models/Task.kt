package org.glenchiridza.task_application.models

import jakarta.persistence.*


@Entity
@Table(name = "task", uniqueConstraints = [UniqueConstraint(name = "uk_task_description", columnNames = ["description"])])
class Task {

    @Id
    @GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
}