package com.open.mind.back.service;

import com.open.mind.back.model.SecurityUser;
import com.open.mind.back.model.User;

/** Interface for user operations. */
public interface UserInterface {
  /**
   * Get SecurityUserModel by email.
   *
   * @param email email.
   * @return SecurityUser.
   */
  SecurityUser getUserByEmail(String email);

  /**
   * Register user.
   *
   * @param user UserModel with credentials.
   * @return UserModel of created user.
   */
  User registerUser(User user);

  /**
   * Update user.
   *
   * @param user UserModel with credentials.
   * @return UserModel of updated user.
   */
  User updateUser(User user);

  /**
   * Check if password is correct.
   *
   * @param user userModel with credentials.
   * @return true if correct.
   */
  boolean isCorrectPassword(User user);

  /**
   * Activate user by email.
   *
   * @param email user email.
   */
  void activateUserByEmail(String email);

  /**
   * Change user password by email.
   *
   * @param email user email.
   * @param newPassword user new password.
   */
  void changeUserPasswordByEmail(String email, String newPassword);

  /**
   * Check if user exist by email.
   *
   * @param email user email.
   * @return true or false.
   */
  boolean userExistByEmail(String email);

  /**
   * Get authenticated user from spring context.
   *
   * @return UserModel.
   */
  User getAuthenticatedUser();
}
