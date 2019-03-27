package ch.wetwer.hermannapi.data.repository;

import ch.wetwer.hermannapi.data.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

}
