package jun.st.ex.Service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DAO.MemberDAO;
import jun.st.ex.Persistence.DTO.MemberDTO;
@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO memberDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<MemberDTO> memberList() {
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		String encryptPassword=passwordEncoder.encode(dto.getPasswd());
		dto.setPasswd(encryptPassword);
	
		memberDao.insertMember(dto);

	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);

	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDao.updateMember(dto);

	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		return memberDao.checkPw(userid, passwd);
	}

	@Override
	public MemberDTO loginCheck(MemberDTO dto) {
	
		return memberDao.loginCheck(dto);
	}

	@Override
	public String findId(String name, String email) {
		return memberDao.findId(name, email);
		
	}

	@Override
	public boolean getUser(String userid) {
		return memberDao.getUser(userid);
	}

	@Override
	public int checkMemberByUserIdAndEmail(MemberDTO dto) {
		return memberDao.checkMemberByUserIdAndEmail(dto);
	}

	@Override
	public int MailUpdateUserPw(MemberDTO dto) {
		return memberDao.MailUpdateUserPw(dto);
	}

	@Override
	public String getUserPW(String userid) {
		return memberDao.getUserPW(userid);
	}

	@Override
	public int UpdateNewPassword(Map<String, String> data) {
		return memberDao.UpdateNewPassword(data);
	}

	@Override
	public List<MemberDTO> memberList(String userId) {
		return memberDao.memberList(userId);
	}

	/*@Override
	public int update_pw(MemberDTO member) throws Exception {
		return memberDao.update_pw(member);
	}*/

	
}
