package jun.st.ex.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DTO.MemberDTO;
import jun.st.ex.Service.MemberService;

@Controller
public class MemberController {

	@Inject
	MemberService memberService;
	
	@RequestMapping("member/list.do") //url mapping
	public String memberList(Model model) {
		List<MemberDTO> list=memberService.memberList();
		model.addAttribute("list", list);
		//   WEB-INF/views/member/member_list.jsp로 포워딩
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
		//System.out.println(dto);		
		memberService.insertMember(dto);
		return "redirect:/member/list.do";
	}
	@RequestMapping("member/login_check.do")
	public ModelAndView login_check(
			MemberDTO dto, HttpSession session,ModelAndView mav) {
		//로그인 성공 true, 실패 false
		String name = memberService.loginCheck(dto);
		
		if (name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("redirect:/");
			//mav.setViewName("/Main");
		}else {
			mav.setViewName("User/Login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	@RequestMapping("member/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();// 세션 초기화
		// 로그인으로 다시 보내기(리다이렉트)
		return "redirect:/member/Login.do";
	}
	
}
