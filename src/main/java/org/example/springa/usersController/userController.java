package org.example.springa.usersController;

import org.example.springa.DBC.useDAO;
import org.example.springa.users.user;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class userController {

    @GetMapping("/user")
    public List<user> fetchAllUse() throws SQLException {
        useDAO rs = new useDAO();
        return rs.setuse();
    }
//    @GetMapping("/user")
//    public  List<user> showAllUse(){
//
//    }
}
