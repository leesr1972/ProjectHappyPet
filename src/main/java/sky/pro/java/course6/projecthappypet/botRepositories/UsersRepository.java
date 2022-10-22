package sky.pro.java.course6.projecthappypet.botRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sky.pro.java.course6.projecthappypet.botModel.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Поиск пользователей по id чата
     * @param chatId - id чата
     * @return Optional<Users>
     */
    Optional<Users> findUsersByChatId(Long chatId);
}
