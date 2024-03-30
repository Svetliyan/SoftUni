package softuni.exam.models.entity;

import softuni.exam.models.enums.VolcanoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private int elevation;
    @Column
    @Enumerated(EnumType.STRING)
    private VolcanoType volcanoType;
    @Column(name = "last_eruption")
    private LocalDate lastEruption;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "volcano")
    private Set<Volcanologist> volcanologists;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Volcanologist> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(Set<Volcanologist> volcanologists) {
        this.volcanologists = volcanologists;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
