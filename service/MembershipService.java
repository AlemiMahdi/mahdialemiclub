package mahdialemiclub.service;

import java.util.List;

import mahdialemiclub.model.Member;
import mahdialemiclub.repository.MemberRegistry;

public class MembershipService {
    private MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public Member addMember(String id, String name, Member.MemberLevel level) {
        try {
            Member member = new Member(id, name, level);
            memberRegistry.addMember(member);
            return member;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Member findMember(String id) {
        try {
            return memberRegistry.findMemberById(id);

        } catch (Exception ex) {
            throw new RuntimeException("Kunde inte hitta medlem: " + ex.getMessage(), ex);
        }
    }

    public List<Member> searchMembers(String name) {
        return memberRegistry.searchMembersByName(name);
    }

    public boolean updateMember(String id, String newName, Member.MemberLevel level) {
        try {
            return memberRegistry.updateMember(id, newName, level);
        } catch (Exception e) {
            throw new RuntimeException("Misslyckades med att uppdatera medlemmet." + e.getMessage());
        }
    }

    public boolean removeMember(String id) {
        try {
            return memberRegistry.removeMember(id);
        } catch (Exception e) {
            throw new RuntimeException("Gick ej att ta bort medlemmet" + e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        return memberRegistry.getAllMembers();
    }

}
