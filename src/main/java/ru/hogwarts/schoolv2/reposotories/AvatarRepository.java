package ru.hogwarts.schoolv2.reposotories;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.schoolv2.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(long id);
}
