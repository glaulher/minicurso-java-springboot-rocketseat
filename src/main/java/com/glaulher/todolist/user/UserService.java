package com.glaulher.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final IUserRepository userRepository;

  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<UserModel> create(UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      System.out.println("User already exists"); // NOSONAR
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    var passwordHashared =
        BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashared);

    return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(userModel));
  }
}
