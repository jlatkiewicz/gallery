package dev.jlatkiewicz.gallery.persistance;

import dev.jlatkiewicz.gallery.domain.Image;
import dev.jlatkiewicz.gallery.domain.ImageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoImageRepository implements ImageRepository {
    private final MongoImageDao mongoImageDao;

    public MongoImageRepository(MongoImageDao mongoImageDao) {
        this.mongoImageDao = mongoImageDao;
    }

    @Override
    public List<Image> list() {
        return mongoImageDao.findAll().stream()
                .map(MongoImage::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<Image> save(Image image) {
        MongoImage mongoImage = MongoImage.fromDomain(image);
        MongoImage insertResult = mongoImageDao.insert(mongoImage);
        return Optional.ofNullable(insertResult).map(MongoImage::toDomain);
    }

    @Override
    public Optional<Image> delete(String imageId) {
        var image = mongoImageDao.findByImageId(imageId);
        image.ifPresent(i -> mongoImageDao.deleteByImageId(i.getImageId()));
        return image.map(MongoImage::toDomain);
    }
}
