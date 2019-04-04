package se.BTH.ITProjectManagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import se.BTH.ITProjectManagement.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    List<User> findByFirstName(@Param("first_name") String firstName);

    List<User> findByLastName(@Param("last_name") String lastName);

    User findByEmail(@Param("email") String email);

    List<User> findByPhone(@Param("phone") String phone);

    List<User> findByCity(@Param("city") String city);


    //@Query("SELECT p FROM Person p where p.firstName = ?1 AND p.lastName = ?2")
    List<User> findByFirstNameAndLastName(@Param("first_name") String fName, @Param("last_name") String lName);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

}
