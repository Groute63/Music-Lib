package run.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class ViewController {

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/addSong")
    public String addSong() throws SQLException {
        return "addSong";
    }
}
