package init;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    List<User> findByLastname(String lastname);
    List <User> findByLogin(String login);
}
