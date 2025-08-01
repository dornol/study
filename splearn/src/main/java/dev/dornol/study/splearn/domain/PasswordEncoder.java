package dev.dornol.study.splearn.domain;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
