package dev.dornol.study.splearn.domain;

public record MemberCreateRequest(
        String email, String nickname, String password
) {
}
