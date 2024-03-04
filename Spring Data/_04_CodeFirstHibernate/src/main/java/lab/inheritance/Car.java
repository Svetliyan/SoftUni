package lab.inheritance;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lab.composition.PlateNumber;

import java.math.BigDecimal;

@Entity
public class Car extends Vehicle {
    private static final String CAR_TYPE = "CAR";

    @Basic
    private int seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car() {}

    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(CAR_TYPE, model, price, fuelType);

        this.seats = seats;
        this.plateNumber = plateNumber;
    }
}
