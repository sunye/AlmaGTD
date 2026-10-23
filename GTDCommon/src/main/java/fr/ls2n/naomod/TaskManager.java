package fr.ls2n.naomod;

import java.util.Date;
import java.util.List;

public interface TaskManager {
    TaskId createTask(String description, Date due, String tag);
    void deleteTask(TaskId id);
    List<TaskId> retrieveAllTasksForUser(String username);
    List<TaskId> retrieveAllTasksForTag(String tag);
    TaskId nextTask();
}
