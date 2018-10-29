package com.mitrais.rms.service.impl;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    UserServiceImpl() {
         userDao = UserDaoImpl.getInstance();
    }

    public Optional<User> find(Long id) {
        return userDao.find(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public String save(User user) {
        String userName = user.getUserName();
        return(userDao.findByUserName(userName).isPresent())?
                user.getUserName() + " already exist":
                (userDao.save(user))?
                        "user name <b>" +  userName + "</b> created":
                        "failed to create <b>" +  userName + "</b>";
    }

    public String update(User user) {
        String userName = user.getUserName();
        return (userDao.update(user))?
                "user name <b>" +  userName + "</b> changed":
                "failed to change <b>" +  userName + "</b> data";
    }

    public String delete(User user) {
        String userName = user.getUserName();
        return (userDao.delete(user))?
                "user name <b>" +  userName + "</b> deleted":
                "failed to delete <b>" +  userName + "</b>";
    }

    public Optional<User> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    private static class SingletonHelper
    {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance()
    {
        return UserServiceImpl.SingletonHelper.INSTANCE;
    }
}
