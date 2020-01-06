package dev.jlatkiewicz.gallery.api;

public class SaveImageRequest {
    private String imageLink;

    public SaveImageRequest() {
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
