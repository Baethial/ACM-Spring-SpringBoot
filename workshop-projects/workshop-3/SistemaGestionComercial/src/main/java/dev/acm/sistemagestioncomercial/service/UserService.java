package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.UserEntity;
import dev.acm.sistemagestioncomercial.persistence.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserEntity updateUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    // Advanced

    public UserEntity getUserByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    public List<UserEntity> getAllUsersByCity(String cityName){
        return userRepository.findAllByCity_Name(cityName);
    }

    public List<UserEntity> getAllUsersByDepartment(String departmentName){
        return userRepository.findAllByCity_Department_Name(departmentName);
    }

    public List<UserEntity> getAllUserByNameContaining(String nameFragment){
        return userRepository.findAllByFirstNameContaining(nameFragment);
    }
}

