package ch.wetwer.hermannapi.controller;

import ch.wetwer.hermannapi.data.repository.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.controller
 * @created 01.04.2019
 **/

@Controller
@RequestMapping("api/image")
public class ImageController {

    private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping(value = "{id}", produces = "image/jpeg")
    public @ResponseBody
    byte[] getImage(@PathVariable("id") String id) throws IOException {
        File file = new File(imageRepository.findImageById(Long.valueOf(id)).getPath());
        return Files.readAllBytes(file.toPath());
    }
}
