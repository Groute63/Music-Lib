package run.controller;

import company.entityclass.role.User;
import company.storage.dao.database.databasestorage.DBStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import run.repo.UserRepo;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class AuthorizationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registrationn")
    public String addUser(User user, Map<String, Object> model) throws NoSuchAlgorithmException {
        User userFromDb = userRepo.findByLogin(user.getLogin());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setPassword(DBStorage.getHash(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login";
    }

}
