package ru.hogwarts.schoolv2.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolv2.model.Avatar;
import ru.hogwarts.schoolv2.model.Student;
import ru.hogwarts.schoolv2.reposotories.AvatarRepository;
import ru.hogwarts.schoolv2.reposotories.StudentRepository;
import ru.hogwarts.schoolv2.service.AvatarService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Optional<Student> st = studentRepository.findById(studentId);
        Student student = st.orElseThrow(() -> new NotFoundException("Студент с id" + studentId + "не найден!"));
        Path filePath = Path.of(avatarsDir, studentId + "+" + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = avatarFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    @Override
    public Avatar findAvatar(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public List<Avatar> findAll() {
        Pageable pageable = PageRequest.of(0, 4);
        return avatarRepository.findAll(pageable).getContent();
    }
}
