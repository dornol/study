package dev.dornol.study.splearn.application.provided;

import dev.dornol.study.splearn.domain.Member;
import dev.dornol.study.splearn.domain.MemberRegisterRequest;

public interface MemberRegister {

    Member register(MemberRegisterRequest registerRequest);

}
