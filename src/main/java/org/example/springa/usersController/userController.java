package org.example.springa.usersController;

import org.example.springa.DBC.useDAO;
import org.example.springa.users.user;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class userController {

    @GetMapping("/user")
    public List<user> fetchAllUse() throws SQLException {
        useDAO rs = new useDAO();
        return rs.getUsers();
    }

    @PostMapping("/postUser")
    public void createUser(@RequestBody user user) throws SQLException {
        useDAO rs = new useDAO();
        rs.setUser(user);
    }

    @PostMapping("/del")
    public void deleteUser(@RequestParam("id") int id) throws SQLException {
        useDAO rs = new useDAO();
        rs.delUser(id);
    }

    @PutMapping("/change/{id}")
    public void changeUser(@PathVariable int  id, @RequestBody user user) {
        useDAO rs = new useDAO();
        rs.changeUser(id, user);
    }

//    @GetMapping("/user")
//    public  List<user> showAllUse(){
//
//    }
}
