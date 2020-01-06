package dev.jlatkiewicz.gallery.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class IdGenerator {
    String geneate() {
        return UUID.randomUUID().toString();
    }
}
