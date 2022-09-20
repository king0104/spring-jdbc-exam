package org.example.main;

import org.example.config.ApplicationConfig;
import org.example.dao.RoleDao;
import org.example.dto.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);

        Role role = new Role();
        role.setRoleId(201);
        role.setDescription("PROGRAMMER");

        int cnt1 = roleDao.insert(role);
        System.out.println(cnt1+"건 입력하셨습니다");

        int cnt2 = roleDao.update(role);
        System.out.println(cnt2+"건 수정하였습니다");

        int cnt3 = roleDao.deleteById(500);
        System.out.println(cnt3+"건 삭제되었습니다");

        Role role1 = roleDao.selectById(102);
        System.out.println(role1);

    }
}
