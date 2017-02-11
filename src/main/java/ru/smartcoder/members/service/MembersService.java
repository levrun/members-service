package ru.smartcoder.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smartcoder.members.model.Member;
import ru.smartcoder.members.repository.MembersRepository;

import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    public List<Member> findAll() {
        return membersRepository.findAll();
    }

    public Member create(Member member) {
        return membersRepository.save(member);
    }

    public Member update(Member member) {
        return membersRepository.save(member);
    }

    public Member get(Long id) {
        return membersRepository.findOne(id);
    }

    public void delete(Long id) {
        membersRepository.delete(id);
    }

}
