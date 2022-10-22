package sky.pro.java.course6.projecthappypet.botController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sky.pro.java.course6.projecthappypet.botModel.Avatar;
import sky.pro.java.course6.projecthappypet.botService.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Контроллер для работы с фотографиями.
 */
@Tag(name = "Avatar Controller", description = "CRUD операции с сущностью Avatar.")
@RestController
@RequestMapping("/happyPet/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    /**
     * Загрузка фотографии питомца
     *
     * @param petId - id питомца
     * @param file  -
     * @return - ResponseEntity<String>
     * @throws IOException - исключение IOException
     */
    @Operation(
            summary = "Загрузка изображения питомца.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Метод загружает изображение питомца в базу данных.",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = Avatar.class)
                            )
                    )
            }
    )
    @PostMapping(value = "/uploadAvatars/{petId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@Parameter(description = "Указываем id питомца.")
                                               @PathVariable Long petId,
                                               @Parameter(description = "Передаем файл с изображением питомца.")
                                               @RequestParam(required = false)
                                               MultipartFile file)
            throws IOException {
        avatarService.upLoadAvatar(petId, file);
        return ResponseEntity.ok().body("Фотографии успешно загружены.");
    }

    /**
     * Получение аватарки питомца по id питомца
     *
     * @param petId         - id питомца
     * @param numberOfPhoto - номер фото
     * @return - ResponseEntity<byte[]>
     */
    @Operation(
            summary = "Получение аватарки питомца.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Метод возвращает аватарку питомца по id и номеру фото."
                    )
            }
    )
    @GetMapping(value = "/getAvatar/{petId}")
    public ResponseEntity<byte[]> getAvatarByPetId(@Parameter(description = "Указываем id питомца.")
                                                   @PathVariable Long petId,
                                                   @Parameter(description = "Указываем номер фото.")
                                                   @RequestParam Integer numberOfPhoto) {
        List<Avatar> avatars = avatarService.getAvatarsByPetId(petId);
        Avatar avatar = avatars.get(numberOfPhoto - 1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPhoto().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).
                body(avatar.getPhoto());
    }

    /**
     * Получение фотографии питомца по id питомца
     *
     * @param petId         - id питомца
     * @param numberOfPhoto - номер фото
     * @param response      - response
     */
    @Operation(
            summary = "Получение фотографии питомца.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Метод возвращает изображение питомца по id и номеру фото."
                    )
            }
    )
    @GetMapping(value = "/getPhoto/{petId}")
    public void getPhotoByPetId(@Parameter(description = "Указываем id питомца.")
                                @PathVariable Long petId,
                                @Parameter(description = "Указываем номер фото.")
                                @RequestParam Integer numberOfPhoto,
                                HttpServletResponse response) {
        List<Avatar> avatars = avatarService.getAvatarsByPetId(petId);
        Avatar avatar = avatars.get(numberOfPhoto - 1);
        Path path = Path.of(avatar.getFilePath());
        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
            is.transferTo(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
