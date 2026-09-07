package mx.edu.taskboard.service;

import mx.edu.taskboard.model.Priority;
import mx.edu.taskboard.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskServiceTest {

    @Test
    void storesTasks() {
        var service = new TaskService();

        service.add(new Task(
                "Prepare demo",
                "Ana",
                Priority.MEDIUM,
                LocalDate.of(2026, 9, 10),
                false
        ));

        assertEquals(1, service.all().size());
    }
}
