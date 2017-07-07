package usermanager.dao;


import usermanager.domain.User;

import java.util.List;

public interface UserDAO {

    List<User> userList(Integer begin, Integer step, String searchName, Integer age);
    void delete(Integer id);
    void save(User user);
    void update(User u);
    Long size(String searchName, Integer age);
}
