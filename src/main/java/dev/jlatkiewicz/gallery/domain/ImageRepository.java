package dev.jlatkiewicz.gallery.domain;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    List<Image> list();
    Optional<Image> save(Image image);
    Optional<Image> delete(String imageId);
}
