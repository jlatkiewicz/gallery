package dev.jlatkiewicz.gallery.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final IdGenerator idGenerator;
    private final ImageRepository imageRepository;

    public ImageService(IdGenerator idGenerator, ImageRepository imageRepository) {
        this.idGenerator = idGenerator;
        this.imageRepository = imageRepository;
    }

    public List<Image> list() {
        return imageRepository.list();
    }

    public Optional<Image> save(String imageLink) {
        return imageRepository.save(new Image(idGenerator.geneate(), imageLink));
    }

    public Optional<String> delete(String imageId) {
        return imageRepository.delete(imageId).map(Image::getImageId);
    }
}