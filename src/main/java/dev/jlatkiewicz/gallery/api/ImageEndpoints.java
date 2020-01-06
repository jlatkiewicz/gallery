package dev.jlatkiewicz.gallery.api;

import dev.jlatkiewicz.gallery.domain.Image;
import dev.jlatkiewicz.gallery.domain.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/images")
class ImageEndpoints {
    private final ImageService imageService;

    ImageEndpoints(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    ResponseEntity<List<Image>> list() {
        return ResponseEntity.ok(imageService.list());
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody SaveImageRequest saveImageRequest) {
        return imageService.save(saveImageRequest.getImageLink())
                .map(this::toSavedResponse)
                .orElseGet(this::errorResponse);
    }

    @DeleteMapping("/{imageId}")
    ResponseEntity<String> delete(@PathVariable("imageId") String imageId) {
        return imageService.delete(imageId)
                .map(ResponseEntity::ok)
                .orElseGet(this::notFoundResponse);
    }

    private ResponseEntity<String> toSavedResponse(Image image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(image.getImageId());
    }

    private ResponseEntity<String> errorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private <T> ResponseEntity<T> notFoundResponse() {
        return ResponseEntity.notFound().build();
    }
}
