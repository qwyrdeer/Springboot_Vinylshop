package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createDate;
    private LocalDate editDate;

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

    @Column(name = "edited_date")
    public LocalDate getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDate editDate) {
        this.editDate = editDate;
    }

    @Column(name = "created_date")
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
