package se.BTH.ITProjectManagement.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import se.BTH.ITProjectManagement.models.Team;
import se.BTH.ITProjectManagement.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    List<User> findByName(@Param("name") String Name);

    User findByEmail(@Param("email") String email);

    List<User> findByPhone(@Param("phone") String phone);

    List<User> findByCity(@Param("city") String city);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

}
