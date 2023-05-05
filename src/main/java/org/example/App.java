package org.example;

import org.example.config.AppConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Rest Template!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> userList = communication.getAllUsers();
        System.out.println(userList);
    }
}
