package sky.pro.java.course6.projecthappypet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import sky.pro.java.course6.projecthappypet.botController.AvatarController;
import sky.pro.java.course6.projecthappypet.botController.InfoController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectHappyPetApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private AvatarController avatarController;

    @Autowired
    private InfoController infoController;

    @Test
    void contextLoads() {
        Assertions.assertThat(infoController).isNotNull();
        Assertions.assertThat(avatarController).isNotNull();
    }

}
