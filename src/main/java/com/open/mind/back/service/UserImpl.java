package com.open.mind.back.service;

import com.open.mind.back.exceptions.CredentialsException;
import com.open.mind.back.mappers.Mapper;
import com.open.mind.back.model.SecurityUser;
import com.open.mind.back.model.User;
import com.open.mind.back.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** UserServiceImpl. */
@Service
@AllArgsConstructor
public class UserImpl implements UserInterface {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /** Test user init. */
  @PostConstruct
  private void init() {
    com.open.mind.back.entity.User testEntity =
        com.open.mind.back.entity.User.builder()
            .email("test@test")
            .password(passwordEncoder.encode("test"))
            .username("test")
            .createdOn(LocalDateTime.now())
            .enabled(true)
            .build();
    if (userRepository.getUserEntityByEmail("test@test").isEmpty()) {
      userRepository.save(testEntity);
    }
  }

  @Override
  public SecurityUser getUserByEmail(String email) {
    return Mapper.I.userEntityToSecurityUserModel(
        userRepository
            .getUserEntityByEmail(email)
            .orElseThrow(
                () ->
                    new CredentialsException(
                        Map.of("email", String.format("User, with email '%s', not found", email)),
                        "Incorrect email")));
  }

  @Override
  public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCreatedOn(LocalDateTime.now());
    user.setEnabled(false);

    return Mapper.I.userEntityToModel(
        Optional.of(userRepository.save(Mapper.I.userModelToEntity(user)))
            .orElseThrow(() -> new RuntimeException("Error while trying to save the user")));
  }

  @Override
  public User updateUser(User user) {
    return Mapper.I.userEntityToModel(
        userRepository.save(
            Mapper.I.updateUserEntityFromModel(
                user,
                userRepository.findById(getAuthenticatedUser().getId().toString()).orElseThrow())));
  }

  /**
   * isCorrectPassword.
   *
   * @param user userModel with credentials.
   * @return boolean.
   */
  public boolean isCorrectPassword(User user) {
    if (!passwordEncoder.matches(
        user.getPassword(), getUserByEmail(user.getEmail()).getPassword())) {
      throw new CredentialsException(
          Map.of("password", "Please enter correct password"), "Incorrect password");
    }
    return true;
  }

  @Override
  public void activateUserByEmail(String email) {
    userRepository.findByEmailAndEnable(email);
  }

  @Override
  public void changeUserPasswordByEmail(String email, String newPassword) {
    userRepository.findByEmailAndChangePassword(email, passwordEncoder.encode(newPassword));
  }

  @Override
  public boolean userExistByEmail(String email) {
    return userRepository.getUserEntityByEmail(email).isPresent();
  }

  @Override
  public User getAuthenticatedUser() {
    return Mapper.I.securityUserModelToModel(
        (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }
}
