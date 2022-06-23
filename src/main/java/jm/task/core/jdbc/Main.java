package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 37);
        userDao.saveUser("Name2", "LastName2", (byte) 24);
        userDao.saveUser("Name3", "LastName3", (byte) 59);
        userDao.saveUser("Name4", "LastName4", (byte) 19);


        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
