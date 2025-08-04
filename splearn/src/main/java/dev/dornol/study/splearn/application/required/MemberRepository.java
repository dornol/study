package dev.dornol.study.splearn.application.required;


import dev.dornol.study.splearn.domain.Member;
import org.springframework.data.repository.Repository;

/**
 * 회원정보를 저장하거나 조회한다.
 */
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);
}
