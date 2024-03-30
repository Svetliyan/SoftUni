package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.VolcanoSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class VolcanoServiceImpl implements VolcanoService {
    private static String FILE_PATH = "src/main/resources/files/json/volcanoes.json";
    private final VolcanoRepository volcanoRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanoRepository = volcanoRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        VolcanoSeedDto[] volcanoSeedDtos = gson.fromJson(new FileReader(FILE_PATH), VolcanoSeedDto[].class);

        for (VolcanoSeedDto volcanoSeedDto : volcanoSeedDtos) {
            Optional<Volcano> optional = this.volcanoRepository.findByName(volcanoSeedDto.getName());
            if (!this.validationUtil.isValid(volcanoSeedDto) || optional.isPresent()){
                stringBuilder.append("Invalid volcano\n");
                continue;
            }

            Volcano volcano = this.modelMapper.map(volcanoSeedDto, Volcano.class);
            this.volcanoRepository.saveAndFlush(volcano);
            stringBuilder.append(String.format("Successfully imported volcano %s of type %s\n",volcanoSeedDto.getName(), volcanoSeedDto.getVolcanoType()));
        }
        return stringBuilder.toString();
    }

    @Override
    public String exportVolcanoes() {
        return this.volcanoRepository
                .findByAboveElevation()
                .stream()
                .map(v -> String.format("Volcano: %s\n" +
                        "   *Located in: %s\n" +
                        "   **Elevation: %s\n" +
                        "   ***Last eruption on: %s\n",v.getName(), v.getCountry().getName(), v.getElevation(), v.getLastEruption()))
                .collect(Collectors.joining());
    }
}