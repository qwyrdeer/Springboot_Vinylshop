package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createDate;
    private LocalDate editDate;
    @Column(nullable = false)
    private String name;

    @PrePersist
    public void onCreate() {
        this.createDate = LocalDate.now();
        this.editDate = LocalDate.now();
    }

    @PreUpdate
    public void onEdit() {
        this.editDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDate editDate) {
        this.editDate = editDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
