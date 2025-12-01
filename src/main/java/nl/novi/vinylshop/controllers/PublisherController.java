package nl.novi.vinylshop.controllers;

import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PublisherEntity>> getAllPublishers() {
        var publishers = publisherService.findAllPublishers();
        return ResponseEntity.ok((List<PublisherEntity>) publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublisherById(@PathVariable Long id) {
        var publisher = publisherService.findPublisherById(id);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PublisherEntity> createPublisher(@RequestBody PublisherEntity publisherInput) {
        var newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePublisher(@PathVariable Long id, @RequestBody PublisherEntity publisher) {
        var updatedPublisher = publisherService.updatePublisher(id, publisher);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublisherEntity> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
