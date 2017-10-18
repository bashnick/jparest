package init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private final UserRepository repository;

    @Autowired
    public UserRestController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm(@RequestParam("username") String username, @RequestParam ("password") String password) throws ServletException, IOException {
        String validateUser = validateUser(username, password);
        log.info("User is determined as: ");
        log.info(validateUser);
        if(validateUser.equals("ADMIN")) return new ModelAndView("redirect:/admin/login");
        else if(validateUser.equals("USER")) return new ModelAndView("redirect:/user/login");
        else return new ModelAndView("redirect:/loginfailure");
    }

    @RequestMapping("/admin/login")
    public String findAll(){
        String result = "";
        for(User user: repository.findAll()){
            result += user.toString();
        }
        return result;
    }

    @RequestMapping("/user/login")
    public String helloUser(){
        String result = "Hello User!";
        return result;
    }

    @RequestMapping("/loginfailure")
    public String lofinFailure(){
        String result = "Wrong username or password";
        return result;
    }


   @RequestMapping(value = "/newusr", method = RequestMethod.POST)
    public String saveNewUser(@RequestBody User user) throws NoSuchAlgorithmException {
        if(repository.findByLogin(user.getLogin()).isEmpty()){
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            user.setPassword(sha256hex);
            repository.save(user);
            return "New user saved";
        }
        else return "User with such username already exists";
    }


    @RequestMapping("/findbyusername")
    public String finById(@RequestParam("username") String username){
        String result = "";
        result = repository.findByLogin(username).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastname){
        String result = "<html>";
        for (User user: repository.findByLastname(lastname)){
            result += "<div>" + user.toString() + "</div>";
        }
        return result + "</html>";
    }

    private String validateUser(String username, String password) {
        log.info("Validating user");
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        if(repository.findByLogin(username).isEmpty()) return "LOGINERROR";
        else if(this.repository.findByLogin(username).get(0).getPassword().equals(sha256hex)){
            if(this.repository.findByLogin(username).get(0).getRole().equals("ADMIN")) return "ADMIN";
            else if(this.repository.findByLogin(username).get(0).getRole().equals("USER")) return "USER";
            else return "UNKNOWN ROLE";
        }
        else return "LOGINERROR";
    }

}
