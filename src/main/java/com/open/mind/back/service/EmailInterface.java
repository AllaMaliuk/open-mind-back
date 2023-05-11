package com.open.mind.back.service;

import com.open.mind.back.model.User;

/**
 * GmailService.
 */
public interface EmailInterface {
  void sendAccountActivationLink(User user);

  void sendAccountForgotPasswordLink(String email);
}
