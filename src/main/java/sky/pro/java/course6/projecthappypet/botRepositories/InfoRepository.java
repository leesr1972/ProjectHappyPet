package sky.pro.java.course6.projecthappypet.botRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sky.pro.java.course6.projecthappypet.botModel.Info;

/**
 * Репозиторий для сущностей Info.
 */
@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
}
