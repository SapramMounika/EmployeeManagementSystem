package Repository;


import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User user) {
       // i added
        return user;
    }

    User findByEmail(String email);
}
