package ca.sheridancollege.rattan.controllers;

import ca.sheridancollege.rattan.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	

    @Autowired
    private DatabaseAccess da;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", da.getBooks());
        return "index"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @PostMapping("/register")
    public String processRegistration(@RequestParam String username, @RequestParam String password) {
        da.addUser(username, encoder.encode(password));
        return "redirect:/login";
    }

    @GetMapping("/permissionDenied")
    public String permissionDenied() {
        return "error/permissionDenied";
    }
    
    
}