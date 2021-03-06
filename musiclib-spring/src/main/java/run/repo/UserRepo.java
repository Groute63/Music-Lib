package run.repo;

import company.entityclass.role.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByLogin(String login);
}
