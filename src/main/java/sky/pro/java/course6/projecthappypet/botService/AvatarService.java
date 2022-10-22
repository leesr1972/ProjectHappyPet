package sky.pro.java.course6.projecthappypet.botService;

import org.apache.commons.io.output.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import sky.pro.java.course6.projecthappypet.botModel.Avatar;
import sky.pro.java.course6.projecthappypet.botModel.Pet;
import sky.pro.java.course6.projecthappypet.botRepositories.AvatarRepository;
import sky.pro.java.course6.projecthappypet.botRepositories.PetRepository;

import javax.imageio.ImageIO;
import javax.ws.rs.NotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    /**
     * Директорий, где будут хранится файлы с фотографиями.
     */
    @Value("${path.to.avatars.folder}")
    private String avatarDir;

    private static final Logger LOGGER = LoggerFactory.getLogger(AvatarService.class);

    private final PetRepository petRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(PetRepository petRepository, AvatarRepository avatarRepository) {
        this.petRepository = petRepository;
        this.avatarRepository = avatarRepository;
    }

    /**
     * Запись фотографий на диск и в таблицу Avatar.
     */
    public void upLoadAvatar(Long petId, MultipartFile avatarFile)
            throws IOException {
        LOGGER.info("Was invoked method for uploading avatar of pet.");

        Pet pet = petRepository.findById(petId).orElseThrow();
        Avatar avatar = new Avatar();
        avatar.setPet(pet);
        String pathOfPet = avatarDir + "/" + petId;

        if (avatarFile != null) {
            Path filePath = Path.of(pathOfPet, pet.getName() +
                    getAvatarsByPetId(petId).size() + "." +
                    getExtension(Objects.requireNonNull(avatarFile.getOriginalFilename())));
            uploadingPhoto(avatarFile, filePath);
            avatar.setFilePath(filePath.toString());
            avatar.setMediaType(avatarFile.getContentType());
            avatar.setFileSize(avatarFile.getSize());
            avatar.setPhoto(creatingSmallerCopyOfPhoto(filePath));
        }

        avatarRepository.save(avatar);
    }

    /**
     * Запись файла с фотографией на диск.
     */
    private void uploadingPhoto(MultipartFile avatarFile, Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
    }

    /**
     * Получение расширения файла с фотографией.
     */
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Создание уменьшенной фотографии для записи в таблицу Avatar.
     */
    public byte[] creatingSmallerCopyOfPhoto(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage smallCopy = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = smallCopy.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(smallCopy, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    /**
     * Получение Автаров по идентификатору питомца.
     */
    public List<Avatar> getAvatarsByPetId(Long petId) {
        LOGGER.info("Was invoked method for getting avatar of pet by petId.");
        Pet pet = petRepository.findById(petId).orElseThrow(NotFoundException::new);
        return avatarRepository.findAvatarByPet(pet);
    }

    /**
     * Сохранение фотографии
     * @param avatar - фото
     * @return Avatar
     */
    public Avatar save(Avatar avatar) {
        LOGGER.info("Was invoked method for save avatar of pet.");
        return avatarRepository.save(avatar);
    }

    /**
     * Удаление фотографии
     * @param avatar - фото
     */
    public void delete(Avatar avatar) {
        LOGGER.info("Was invoked method for delete avatar of pet.");
        avatarRepository.delete(avatar);
    }

    /**
     * Поиск фото по id
     * @param id - id фото
     * @return Avatar
     */
    public Avatar getAvatarById(Long id) {
        LOGGER.info("Was invoked method for get avatar of pet by id.");
        return avatarRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
