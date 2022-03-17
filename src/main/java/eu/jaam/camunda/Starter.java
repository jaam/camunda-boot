package eu.jaam.camunda;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class Starter implements InitializingBean {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryService historyService;

    public Starter(RuntimeService runtimeService, TaskService taskService, HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.historyService = historyService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 500; i++) {
            runtimeService.startProcessInstanceByKey("random");
        }
        List<Task> list = taskService.createTaskQuery().active().list();
        for (Task task : list) {
//            taskService.complete(task.getId());
        }

        long completedProcesses = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("key").completed().count();
        List<HistoricTaskInstance> completedTasks = historyService.createHistoricTaskInstanceQuery().processDefinitionKey("key").processFinished().list();

        List<HistoricTaskInstance> completedLast3Tasks = historyService.createHistoricTaskInstanceQuery().processDefinitionKey("random").processFinished().list();
        System.out.println("jarl");


    }
}
