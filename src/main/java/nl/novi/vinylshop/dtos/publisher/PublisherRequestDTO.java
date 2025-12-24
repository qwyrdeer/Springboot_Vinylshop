package nl.novi.vinylshop.dtos.publisher;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class PublisherRequestDTO {
    @Id
    private Long id;

    @NotBlank
    @Max(value = 50, message = "Naam mag niet langer zijn dan 50 karakters")
    private String name;
    private String address;
    private String contactDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
