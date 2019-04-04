package se.BTH.ITProjectManagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import se.BTH.ITProjectManagement.models.BacklogTask;
import se.BTH.ITProjectManagement.models.Task;
import se.BTH.ITProjectManagement.models.Team;

import java.util.Optional;

public interface BacklogTaskRepository extends MongoRepository<BacklogTask,String> {

    Optional<BacklogTask> findByTeam(@Param("team")Team team);

    Optional<BacklogTask> findByTasks(@Param("tasks") Task task);
}
