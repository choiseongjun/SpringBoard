package jun.st.ex.Service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DAO.MemberDAO;
import jun.st.ex.Persistence.DTO.MemberDTO;
@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO memberDao;
	
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
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
	public String loginCheck(MemberDTO dto) {
		return memberDao.loginCheck(dto);
	}

}
