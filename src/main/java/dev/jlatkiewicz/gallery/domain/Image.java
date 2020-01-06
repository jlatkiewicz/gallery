package dev.jlatkiewicz.gallery.domain;

public class Image {
    private final String imageId;
    private final String imageLink;

    public Image(String imageId, String imageLink) {
        this.imageId = imageId;
        this.imageLink = imageLink;
    }

    public String getImageId() {
        return imageId;
    }

    public String getImageLink() {
        return imageLink;
    }
}
