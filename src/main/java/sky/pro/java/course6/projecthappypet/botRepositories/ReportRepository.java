package sky.pro.java.course6.projecthappypet.botRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.java.course6.projecthappypet.botModel.Report;
import sky.pro.java.course6.projecthappypet.botModel.Users;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    /**
     * Поиск отчетов по владельцу питомца.
     */
    List<Report> findReportsByUser(Users user);
}
