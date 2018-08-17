package jun.st.ex.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import jun.st.ex.Persistence.DTO.MemberDTO;

public interface MemberService {
	public List<MemberDTO> memberList();
	public void insertMember(MemberDTO dto);
	public MemberDTO viewMember(String userid);
	public void deleteMember(String userid);
	public void updateMember(MemberDTO dto);
	public boolean checkPw(String userid, String passwd);
	public String loginCheck(MemberDTO dto);
	public void logout(HttpSession session);
}
