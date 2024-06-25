package com.faqcodes.agilesoft.users.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.users.application.SigninUseCase;
import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.security.JwtTokenProvider;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.SigninRequest;
import com.faqcodes.agilesoft.users.models.SigninResponse;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SigninConfiguration {

  @Bean
  JwtTokenProvider jwtTokenProvider() {
    return new JwtTokenProvider();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
      }
    };
  }

  @Bean
  UseCase<SigninRequest, SigninResponse> signinUseCase(
      JwtTokenProvider jwtTokenProvider,
      PasswordEncoder passwordEncoder,
      UserRepository repository) {
    return new SigninUseCase(jwtTokenProvider, passwordEncoder, repository);
  }

}
