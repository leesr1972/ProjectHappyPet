package sky.pro.java.course6.projecthappypet.botRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sky.pro.java.course6.projecthappypet.botModel.Avatar;
import sky.pro.java.course6.projecthappypet.botModel.Pet;

import java.util.List;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    /**
     * Поиск фото по питомцу
     * @param pet - питомец
     * @return - List<Avatar>
     */
    List<Avatar> findAvatarByPet(Pet pet);
}
