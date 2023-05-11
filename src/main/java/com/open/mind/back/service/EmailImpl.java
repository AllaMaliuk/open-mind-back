package com.open.mind.back.service;

import com.open.mind.back.model.JwtType;
import com.open.mind.back.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** GmailServiceImpl. */
@Service
@RequiredArgsConstructor
public class EmailImpl implements EmailInterface {
  private final JavaMailSender emailSender;
  private final TokenInterface tokenInterface;

  private void sendMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }

  @Override
  public void sendAccountActivationLink(User user) {
    sendMessage(
        user.getEmail(),
        "Activate your account",
        "Click here to activate your account:"
            + " http://localhost:3000/account/email/activation/"
            + tokenInterface.createJwt(user.getEmail(), JwtType.EMAIL_ACTIVATION));
  }

  @Override
  public void sendAccountForgotPasswordLink(String email) {
    sendMessage(
        email,
        "Change your password",
        "Click here to change your password:"
            + "http://localhost:3000/account/password/forget/"
            + tokenInterface.createJwt(email, JwtType.PASSWORD_FORGOT));
  }
}
