package org._08_json_productshop.service.impl;

import com.google.gson.Gson;
import org._08_json_productshop.data.entities.User;
import org._08_json_productshop.data.repositories.UserRepository;
import org._08_json_productshop.service.UserService;
import org._08_json_productshop.service.dtos.UserSeedDto;
import org._08_json_productshop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/json/users.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    public void seedUsers() throws FileNotFoundException {
        if (this.userRepository.count() == 0){
            UserSeedDto[] userSeedDtos = this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDto[].class);
            for (UserSeedDto userSeedDto : userSeedDtos) {
                if (!this.validationUtil.isValid(userSeedDto)){
                    this.validationUtil.getViolations(userSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.userRepository.saveAndFlush(this.modelMapper.map(userSeedDto, User.class));
            }
        }
    }
}
