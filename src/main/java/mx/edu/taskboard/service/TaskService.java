package mx.edu.taskboard.service;

import mx.edu.taskboard.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> all() {
        return List.copyOf(tasks);
    }
}
