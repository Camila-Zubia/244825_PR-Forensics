package mx.edu.taskboard.service;

import mx.edu.taskboard.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        Task normalized = new Task(
                task.title(),
                task.assignee().trim(),
                task.priority(),
                task.dueDate(),
                task.completed()
        );
        tasks.add(normalized);
    }

    public List<Task> all() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::priority).reversed())
                .toList();
    }

    public List<Task> overdue(LocalDate today) {
        return tasks.stream()
                .filter(task -> !task.completed())
                .filter(task -> task.dueDate().isBefore(today))
                .toList();
    }
}
