package entities.ex4_HospitalDatabase;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {
    @Column
    private String name;

    @Column
    private String comments;

    @OneToMany(mappedBy = "diagnose")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(name = "diagnose_medicaments",
    joinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicaments;
}
