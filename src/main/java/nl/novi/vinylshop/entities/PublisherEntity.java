package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Publishers")
public class PublisherEntity extends BaseEntity {
        @Column(nullable=false)
        private String name;
        private String address;
        private String contactDetails;


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
