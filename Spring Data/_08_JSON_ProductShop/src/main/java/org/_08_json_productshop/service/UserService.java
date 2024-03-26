package org._08_json_productshop.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface UserService {
    void seedUsers() throws FileNotFoundException;
}
