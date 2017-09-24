package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/save")
    public String process(){
        repository.save(new User("Jack", "Smith", "user"));
        repository.save(new User("Adam", "Johnson", "user"));
        repository.save(new User("Kim", "Smith", "user"));
        repository.save(new User("David", "Williams", "user"));
        repository.save(new User("Peter", "Davis", "user"));
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
