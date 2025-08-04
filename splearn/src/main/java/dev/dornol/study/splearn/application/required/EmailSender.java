package dev.dornol.study.splearn.application.required;

import dev.dornol.study.splearn.domain.Email;

/**
 * 이메일을 발송
 */
public interface EmailSender {

    void send(Email email, String subject, String body);

}
