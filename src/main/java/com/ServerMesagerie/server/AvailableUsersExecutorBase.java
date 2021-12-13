package com.ServerMesagerie.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AvailableUsersExecutorBase {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void atStartup() {
        AvailableUsersManager task = applicationContext.getBean(AvailableUsersManager.class);
        taskExecutor.execute(task);

    }
}


