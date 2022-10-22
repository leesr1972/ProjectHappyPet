package sky.pro.java.course6.projecthappypet.botService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import sky.pro.java.course6.projecthappypet.botModel.Report;
import sky.pro.java.course6.projecthappypet.botModel.Users;
import sky.pro.java.course6.projecthappypet.botRepositories.ReportRepository;

import java.util.List;

@Service
public class ReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;


    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Поиск отчета по пользователю
     * @param user - пользователь
     * @return List<Report>
     */
    public List<Report> getReportsByUser(Users user) {
        LOGGER.info("Was invoked method for get report by user.");
        return reportRepository.findReportsByUser(user);
    }

    /**
     * Сохранение отчета
     * @param report - отчет
     * @return Report
     */
    public Report save(Report report) {
        LOGGER.info("Was invoked method for save report.");
        return reportRepository.save(report);
    }

    /**
     * Удаление отчета
     * @param report - отчет
     */
    public void delete(Report report) {
        LOGGER.info("Was invoked method for delete report.");
        reportRepository.delete(report);
    }
}
