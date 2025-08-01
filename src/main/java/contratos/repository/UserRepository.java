package contratos.repository;

import contratos.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    UserDetails findByLogin(String login);

    Boolean existsByLogin(String login);


}