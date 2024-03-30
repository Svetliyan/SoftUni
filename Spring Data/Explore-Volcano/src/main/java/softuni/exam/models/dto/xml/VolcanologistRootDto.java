package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistRootDto {
    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDto> volcanologistSeedDtos;

    public List<VolcanologistSeedDto> getVolcanologistDtos() {
        return volcanologistSeedDtos;
    }

    public void setAstronomerSeedDtos(List<VolcanologistSeedDto> volcanologistSeedDtos) {
        this.volcanologistSeedDtos = volcanologistSeedDtos;
    }
}
