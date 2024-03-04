package lab.composition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lab.inheritance.Car;

import java.math.BigInteger;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;

    @OneToOne(mappedBy = "plateNumber")
    private Car car;

    public PlateNumber() {}

    public PlateNumber(String number) {
        this.number = number;
    }
}
