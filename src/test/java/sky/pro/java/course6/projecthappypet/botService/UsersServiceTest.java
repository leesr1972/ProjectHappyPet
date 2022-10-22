package sky.pro.java.course6.projecthappypet.botService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import sky.pro.java.course6.projecthappypet.botModel.Users;
import sky.pro.java.course6.projecthappypet.botRepositories.CatUsersRepository;
import sky.pro.java.course6.projecthappypet.botRepositories.DogUsersRepository;
import sky.pro.java.course6.projecthappypet.botRepositories.UsersRepository;

import java.util.Optional;

import static sky.pro.java.course6.projecthappypet.botService.ServiceTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private CatUsersRepository catUsersRepository;

    @Mock
    private DogUsersRepository dogUsersRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService out;

    @BeforeEach
    void init() {
        MAKSIM.setChatId(1L);
        MAKSIM.setId(1L);
        MAKSIM.setUserName("Maksim");
    }

    @Test
    void save() {
        when(usersRepository.save(Mockito.any())).thenReturn(Mockito.any());
        out.save(MAKSIM);
        verify(usersRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        doNothing().when(usersRepository).delete(Mockito.any());
        out.delete(MAKSIM);
        verify(usersRepository, times(1)).delete(any());
    }

    @Test
    void getUsersByChatId() {
        when(usersRepository.findUsersByChatId(Mockito.anyLong())).thenReturn(Optional.of(MAKSIM));
        Optional<Users> usersByChatId = out.getUsersByChatId(1L);
        assertEquals(MAKSIM.getId(), usersByChatId.get().getId());
        verify(usersRepository, times(1)).findUsersByChatId(anyLong());
    }
}
