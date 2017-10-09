package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/login")
    public String login(@RequestParam("login") String login){
        String result = "<html>Hello ";
        result += repository.findByLogin(login).toString();
        return result +"</html>";
    }


    @RequestMapping("/save")
    public String process(){
        repository.save(new User("Jack", "Smith", "jackS","jacks@mail.ru", "USER"));
        repository.save(new User("Adam", "Johnson", "adamJ", "adam@mail.ru", "USER"));
        return "Done";
    }

    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";
        for(User user: repository.findAll()){
            result += "<div>" + user.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/findbyid")
    public String finById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "<html>";
        for (User user: repository.findByLastName(lastName)){
            result += "<div>" + user.toString() + "</div>";
        }
        return result + "</html>";
    }

}
