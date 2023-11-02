package edu.syr.oodproject.trelloclonesu.validator;

import edu.syr.oodproject.trelloclonesu.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskValidator {
    private   static  final String STATE_CHANGED = "TASK STATE CHANGED ";
    private   static  final String DESCRIPTION_MODIFIED = " DESCRIPTION MODIFIED";

    public static boolean validateInputBeforeInsert(Task task){
        return  (task.getDescription().length() !=0 &&
                task.getStatus().toString().length() !=0  &&
                task.getHistories().size() ==0 &&
                //task.getComments().size() == 0 &&
                task.getCompletionDate().equals(null));
    }

    public static String validateAndGetMessage(Task before, Task updated){
        StringBuilder message = new StringBuilder("");
        boolean stateChange = before.getStatus().equals(updated.getStatus());
        boolean descriptionModified = before.getDescription().equals(updated.getDescription());
        if(stateChange){
            message.append(STATE_CHANGED).append(" from ").append(before.getStatus())
                    .append(" to ").append(updated.getStatus());
        }
        if(descriptionModified)
            if(stateChange)
                message.append(" and ");
            message.append(DESCRIPTION_MODIFIED);
        return message.toString();
    }

}
