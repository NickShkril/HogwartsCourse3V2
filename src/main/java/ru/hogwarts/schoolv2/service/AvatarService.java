package ru.hogwarts.schoolv2.service;


import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolv2.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(long studentId);

    List<Avatar> findAll();
}
