package jun.st.ex.Persistence.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jun.st.ex.Persistence.DTO.MemberDTO;
@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO dto) {
		sqlSession.insert("member.insertMember", dto);

	}

	@Override
	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.ViewUser",userid);
	}

	@Override
	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid); 
	}

	@Override
	public void updateMember(MemberDTO dto) {
		sqlSession.update("member.updateUser",dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		boolean result=false;
		//mapper에 2개 이상의 자료를 전달할 때 : map, dto 사용
		Map<String,String> map=new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count=sqlSession.selectOne("member.checkPw", map);
		//비번이 맞으면 1=>true, 틀리면 0=>false 리턴
		if(count==1) result=true;
		return result;
	}

	@Override
	public MemberDTO loginCheck(MemberDTO dto) {
		return sqlSession.selectOne("member.login_check",dto);
	}

	@Override
	public String findId(String name, String email) {
		Map<String,String> map=new HashMap<>();
		map.put("name", name);
		map.put("email",email);
		return sqlSession.selectOne("member.findid",map);
	}

	@Override
	public boolean getUser(String userid) {
		boolean result=false;
		Map<String,String> map=new HashMap<>();
		map.put("id", userid);
		int count=sqlSession.selectOne("member.getCheakId",userid);
		if(count==1) result=true;
		return result;
	}
	/*@Transactional
	@Override
	public int update_pw(MemberDTO member) throws Exception {
		return sqlSession.update("member.update_pw", member);
	}*/

	@Override
	public int checkMemberByUserIdAndEmail(MemberDTO dto) {
		
		return sqlSession.selectOne("member.checkMemberByUserIdAndEmail",dto);
	}

	@Override
	public int MailUpdateUserPw(MemberDTO dto) {
		return sqlSession.update("member.MailUpdateUserPw",dto);
	}


}
