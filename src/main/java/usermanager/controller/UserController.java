package usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usermanager.dao.UserDAO;
import usermanager.domain.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        user.setDate(new Timestamp(new Date().getTime()));
        userDAO.save(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll(@RequestParam("from") int from,
                             @RequestParam("step") int step,
                             @RequestParam("name") String name,
                             @RequestParam("age") int age) throws Exception {
        return userDAO.userList(from, step, name, age);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody User user) {
        if (!id.equals("undefined")) {
            userDAO.update(user);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer id) {
        userDAO.delete(id);
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public long size(@RequestParam("name") String name,
                     @RequestParam("age") int age) {
        return userDAO.size(name, age);
    }
}
