package mahdialemiclub.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mahdialemiclub.model.Member;

public class MemberRegistry {
    private Map<String, Member> members;

    public MemberRegistry() {
        this.members = new HashMap<>();
    }

    public Member addMember(String id, String name, Member.MemberLevel level) {

        if (members.containsKey(id))
            throw new IllegalArgumentException("Medlem med ID " + id + " finns redan i registret.");

        Member member = new Member(id, name, level);
        members.put(id, member);
        return member;

    }

    public Member findMemberById(String id) {
        Member member = members.get(id);
        if (member == null) {
            throw new IllegalArgumentException("Medlem med detta ID " + id + " finns ej.");
        }
        return member;
    }

    public List<Member> searchMembersByName(String name) {
        String target = name.toLowerCase();
        return members.values().stream()
                .filter(member -> member.getName().toLowerCase().contains(target))
                .collect(Collectors.toList());
    }

    public boolean updateMember(String id, String newName, Member.MemberLevel newLevel) {
        Member member = members.get(id);
        if (member != null) {
            member.setName(newName);
            member.setLevel(newLevel);
            return true;
        }
        return false;
    }

    public boolean removeMember(String id) {
        return members.remove(id) != null;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

}
