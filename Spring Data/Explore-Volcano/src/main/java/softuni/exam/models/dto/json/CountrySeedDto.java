package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Volcano;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CountrySeedDto implements Serializable {
    @Expose
    @Size(min = 3, max = 30)
    @NotNull
    private String name;
    @Expose
    @Size(min = 3, max = 30)
    private String capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String country) {
        this.capital = capital;
    }
}
