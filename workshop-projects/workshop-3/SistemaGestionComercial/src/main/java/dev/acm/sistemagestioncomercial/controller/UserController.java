package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.UserEntity;
import dev.acm.sistemagestioncomercial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.saveUser(userEntity));
    }

    @DeleteMapping ("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.updateUser(userEntity));
    }

    // Advanced

    @GetMapping("/last_name/")
    public ResponseEntity<UserEntity> getUserByLastName(@RequestParam String lastName){
        return ResponseEntity.ok(userService.getUserByLastName(lastName));
    }

    @GetMapping("/city/")
    public ResponseEntity<List<UserEntity>> getAllUsersByCity(@RequestParam String city){
        return ResponseEntity.ok(userService.getAllUsersByCity(city));
    }

    @GetMapping("/department/")
    public ResponseEntity<List<UserEntity>> getAllUsersByDepartment(@RequestParam String department){
        return ResponseEntity.ok(userService.getAllUsersByDepartment(department));
    }

    @GetMapping("/name/")
    public ResponseEntity<List<UserEntity>> getUserByNameContaining(@RequestParam String nameFragment){
        return ResponseEntity.ok(userService.getAllUserByNameContaining(nameFragment));
    }
}
