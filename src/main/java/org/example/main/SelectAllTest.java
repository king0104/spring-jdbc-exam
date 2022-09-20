package org.example.main;

import org.example.config.ApplicationConfig;
import org.example.dao.RoleDao;
import org.example.dto.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SelectAllTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);
        List<Role> roles = roleDao.selectAll();

        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
