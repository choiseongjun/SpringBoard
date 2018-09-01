package jun.st.ex.Service;

import java.util.List;
import java.util.Map;


import jun.st.ex.Persistence.DTO.MemberDTO;

public interface MemberService {
	public List<MemberDTO> memberList();
	public void insertMember(MemberDTO dto);
	public MemberDTO viewMember(String userid);
	public void deleteMember(String userid);
	public void updateMember(MemberDTO dto);
	public boolean checkPw(String userid, String passwd);
	public MemberDTO loginCheck(MemberDTO dto);
	public String findId(String name,String email);
	public boolean getUser(String userid);
	/*public int update_pw(MemberDTO member) throws Exception;*/
	public int checkMemberByUserIdAndEmail(MemberDTO dto);
	public int MailUpdateUserPw(MemberDTO dto);
	public String getUserPW(String userid);
	public int UpdateNewPassword(Map<String, String> data);
}
