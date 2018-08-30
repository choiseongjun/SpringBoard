package jun.st.ex.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DAO.AdminDAO;
import jun.st.ex.Persistence.DTO.MemberDTO;
import jun.st.ex.Service.MemberService;
import jun.st.ex.Service.SesssionEventListener;

@Controller
public class MemberController {

	@Inject
	MemberService memberService;
	@Inject
	AdminDAO adminDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/*@RequestMapping(value = "/find_pw.do", method = RequestMethod.POST)
	public void find_pw(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception{
		service.find_pw(response, member);
	}*/

	
	
	
	@RequestMapping("member/list.do") //url mapping
	public String memberList(Model model) {
		List<MemberDTO> list=memberService.memberList();
		model.addAttribute("list", list);
		//   WEB-INF/views/member/member_list.jsp�� ������
		return "Admin/member_list";
	}
	@RequestMapping("member/register.do") 
	public String write() {
		return "User/Register";
	}
	@RequestMapping("member/Login.do")
	public String Login() {
		return "User/Login";
	}
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {		
		
		
		memberService.insertMember(dto);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("member/login_check.do")
	public ModelAndView login_check(
			MemberDTO dto, HttpSession session,
			ModelAndView mav,MemberDTO param,
			HttpServletRequest request,HttpServletResponse response) {
		
	
		request.getSession().setAttribute("TAATLoginId", param.getUserid());
		
		SesssionEventListener listener = new SesssionEventListener();
        request.getSession().setAttribute(param.getUserid(), listener);  
		
        MemberDTO user = memberService.loginCheck(dto);
		
      //암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = user.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		
		//로그인 시도하려는 아이디가 존재하면..
		 if(user!=null) {
			
			//비밀번호 일치 시(로그인성공)
			if(loginResult) {
				session.setAttribute("userid", dto.getUserid());
				session.setAttribute("name", user.getName());
				mav.setViewName("redirect:/");
			}else {
				mav.setViewName("User/Login");
				/*response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
				out.flush();
				*/
			}
          /*  session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", admin);*/
		}	
		return mav;	
	}
	@RequestMapping("member/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/Login.do";
	}
	@RequestMapping("member/view.do")
	public String viewuser(@RequestParam String userid, Model model) {
		// 모델에 저장
		model.addAttribute("dto", memberService.viewMember(userid));
		// view.jsp에 포워딩
		return "User/UpdateUser";
	}
	@RequestMapping("member/update.do")
	public String update(MemberDTO dto, Model model) {
		
		MemberDTO db = memberService.loginCheck(dto);
		//암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = db.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		
		//비밀번호 체크
/*		boolean result=
memberService.checkPw(dto.getUserid(), dto.getPasswd());
*/		
		
		if(loginResult) { //비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);
			//수정 후 목록으로 이동
			return "redirect:/";
		}else  { //비밀번호가 틀리면
			model.addAttribute("dto", dto);
			model.addAttribute("join_date"
, memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "회원정보가 수정되었습니다.");
			return "User/UpdateUser"; //forward
		}
	}
	@RequestMapping("member/delete.do")
	public String delete(
			String userid, String passwd, Model model,HttpSession session) {
		boolean result=memberService.checkPw(userid, passwd);
		if(result) { //비번이 맞으면 삭제 =>세션 끊고=> 목록으로 이동
			memberService.deleteMember(userid);
			session.invalidate();
			return "redirect:/";
		}else { //비번이 틀리면 되돌아감
			model.addAttribute("message","비밀번호를 확인하세요.");
			model.addAttribute(
					"dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
	@RequestMapping("member/findid.do")
	public String findid() {
		return "User/find";
	}
	@RequestMapping("member/findpw.do")
	public String findpw() {
		return "User/searchPw";
	}
	@RequestMapping(value="member/findidimpl.do",method = RequestMethod.POST)
		public @ResponseBody String findidImpl(String name,String email)throws Exception {

		System.out.println(name+email);
	String idlist = (String) memberService.findId(name,email);
	
		return idlist;
		}
	@ResponseBody
    @RequestMapping(value="member/checkId.do",method = RequestMethod.POST)
	 public String checkSignup(String userid, Model model) {
		
      System.out.println("아이디는ㄴ"+userid);
        boolean result=memberService.getUser(userid);
        System.out.println(result);
		return String.valueOf(result).trim();
        
	}
	
}
