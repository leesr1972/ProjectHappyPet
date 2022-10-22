package sky.pro.java.course6.projecthappypet.botService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.java.course6.projecthappypet.botRepositories.ReportRepository;

import java.util.List;

import static sky.pro.java.course6.projecthappypet.botService.ServiceTestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService out;

    @Test
    void getReportsByUser() {
        when(reportRepository.findReportsByUser(MAKSIM)).thenReturn(List.of(ONE));
        out.getReportsByUser(MAKSIM);
        verify(reportRepository, times(1)).findReportsByUser(any());
    }

    @Test
    void save() {
        when(reportRepository.save(Mockito.any())).thenReturn(Mockito.any());
        out.save(ONE);
        verify(reportRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        doNothing().when(reportRepository).delete(Mockito.any());
        out.delete(TWO);
        verify(reportRepository, times(1)).delete(any());
    }
}
