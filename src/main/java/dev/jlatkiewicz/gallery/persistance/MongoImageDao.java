package dev.jlatkiewicz.gallery.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoImageDao extends MongoRepository<MongoImage, String> {
    Optional<MongoImage> findByImageId(String imageId);
    void deleteByImageId(String imageId);
}
