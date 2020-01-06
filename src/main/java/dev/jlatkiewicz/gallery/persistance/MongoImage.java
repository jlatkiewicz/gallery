package dev.jlatkiewicz.gallery.persistance;

import dev.jlatkiewicz.gallery.domain.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
class MongoImage {
    @Id
    private final String imageId;
    private final String imageLink;

    MongoImage(String imageId, String imageLink) {
        this.imageId = imageId;
        this.imageLink = imageLink;
    }

    String getImageId() {
        return imageId;
    }

    String getImageLink() {
        return imageLink;
    }

    Image toDomain() {
        return new Image(imageId, imageLink);
    }

    static MongoImage fromDomain(Image image) {
        return new MongoImage(image.getImageId(), image.getImageLink());
    }
}
