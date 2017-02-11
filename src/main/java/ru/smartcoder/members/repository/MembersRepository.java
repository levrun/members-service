package ru.smartcoder.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartcoder.members.model.Member;

import java.util.Collection;

@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {
    Collection<Member> findByLastName(String name);
}
