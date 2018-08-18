package jun.st.ex.Controller;

import java.lang.reflect.Member;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DTO.MemberDTO;
import jun.st.ex.Service.MemberService;
import jun.st.ex.Service.SesssionEventListener;

@Controller
public class MemberController {

	@Inject
	MemberService memberService;
	
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
			MemberDTO dto, HttpSession session,ModelAndView mav,MemberDTO param,HttpServletRequest request) {
		
		String name = memberService.loginCheck(dto);
		
		if (name != null) {
			request.getSession().setAttribute("TAATLoginId", param.getUserid());
            
			SesssionEventListener listener = new SesssionEventListener();
            request.getSession().setAttribute(param.getUserid(), listener);                
 

			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("User/Login");
			mav.addObject("message", "error");
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
		//비밀번호 체크
		boolean result=
memberService.checkPw(dto.getUserid(), dto.getPasswd());
		if(result) { //비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);
			//수정 후 목록으로 이동
			return "redirect:/member/list.do"; //redirect
		}else { //비밀번호가 틀리면
			model.addAttribute("dto", dto);
			model.addAttribute("join_date"
, memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "member/view"; //forward
		}
	}
	@RequestMapping("member/delete.do")
	public String delete(
			String userid, String passwd, Model model) {
		boolean result=memberService.checkPw(userid, passwd);
		if(result) { //비번이 맞으면 삭제 => 목록으로 이동
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else { //비번이 틀리면 되돌아감
			model.addAttribute("message","비밀번호를 확인하세요.");
			model.addAttribute(
					"dto", memberService.viewMember(userid));
			return "member/view";
		}
	}
}
