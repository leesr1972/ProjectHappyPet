package sky.pro.java.course6.projecthappypet.botRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sky.pro.java.course6.projecthappypet.botModel.DogUsers;

import java.util.List;
import java.util.Optional;

public interface DogUsersRepository extends JpaRepository<DogUsers, Long> {

    /**
     * Поиск питомца по id чата
     * @param chatId - id чата
     * @return Optional<DogUsers>
     */
    Optional<DogUsers> findDogUsersByChatId(Long chatId);

    /**
     * Поиск всех владельцев собак
     * @return - List<Long>
     */
    @Query("select u.id from DogUsers u where u.pet is not null")
    List<Long> findAllIdWithPet();
}
