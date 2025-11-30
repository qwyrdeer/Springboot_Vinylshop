package nl.novi.vinylshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="Publishers")
public class PublishEntity extends BaseEntity {
        private String address;
        private String contactDetails;


}
