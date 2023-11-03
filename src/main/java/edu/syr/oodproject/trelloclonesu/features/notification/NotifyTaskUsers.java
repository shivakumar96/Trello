package edu.syr.oodproject.trelloclonesu.features.notification;
import edu.syr.oodproject.trelloclonesu.models.Task;
import edu.syr.oodproject.trelloclonesu.models.User;

import java.util.List;

public class NotifyTaskUsers {
    Task task;
    public NotifyTaskUsers(Task task){
     this.task = task;
    }
    public void notifyAllUser(String message){
        List<User> userList = task.getAssignedTo();
        for(User user: userList)
            user.notifyUser(message);
    }
}
