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

        //get
        List<User> userList = communication.getAllUsers();
        System.out.println(userList);

        //post
        User user = new User("James", "Brown", (byte) 25);
        communication.saveUser(new User("James", "Brown", (byte) 25));

        //put
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUser(user);
    }
}
