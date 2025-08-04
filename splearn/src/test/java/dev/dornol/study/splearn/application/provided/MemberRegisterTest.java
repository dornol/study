package dev.dornol.study.splearn.application.provided;

import dev.dornol.study.splearn.application.MemberService;
import dev.dornol.study.splearn.application.required.EmailSender;
import dev.dornol.study.splearn.application.required.MemberRepository;
import dev.dornol.study.splearn.domain.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MemberRegisterTest implements WithAssertions {

    @Test
    void registerTestStub() {
        PasswordEncoder passwordEncoder = MemberFixture.createPasswordEncoder();
        MemberRegister service = new MemberService(
                new MemberRepositoryStub(),
                new EmailSenderStub(),
                passwordEncoder
        );

        Member member = service.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void registerTestMock() {
        PasswordEncoder passwordEncoder = MemberFixture.createPasswordEncoder();
        EmailSenderMock emailSender = new EmailSenderMock();
        MemberRegister service = new MemberService(
                new MemberRepositoryStub(),
                emailSender,
                passwordEncoder
        );

        Member member = service.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        assertThat(emailSender.getTos()).hasSize(1);
        assertThat(emailSender.getTos().getFirst()).isEqualTo(member.getEmail());
    }

    @Test
    void registerTestMockito() {
        PasswordEncoder passwordEncoder = MemberFixture.createPasswordEncoder();
        EmailSender emailSenderMock = mock(EmailSender.class);

        MemberRegister service = new MemberService(
                new MemberRepositoryStub(),
                emailSenderMock,
                passwordEncoder
        );

        Member member = service.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
        verify(emailSenderMock).send(eq(member.getEmail()), any(), any());
    }

    static class MemberRepositoryStub implements MemberRepository {
        @Override
        public Member save(Member member) {
            ReflectionTestUtils.setField(member, "id", 1L);
            return member;
        }
    }

    static class EmailSenderStub implements EmailSender {
        @Override
        public void send(Email email, String subject, String body) {
        }
    }

    static class EmailSenderMock implements EmailSender {
        List<Email> tos = new ArrayList<>();

        public List<Email> getTos() {
            return tos;
        }

        @Override
        public void send(Email email, String subject, String body) {
            tos.add(email);
        }
    }

}