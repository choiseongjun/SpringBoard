package jun.st.ex.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jun.st.ex.Persistence.DTO.Email;
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
			HttpServletRequest request,HttpServletResponse response
			) throws IOException {
		
		request.getSession().setAttribute("TAATLoginId", param.getUserid());
		
		SesssionEventListener listener = new SesssionEventListener();
        request.getSession().setAttribute(param.getUserid(), listener);  
		
        MemberDTO user = memberService.loginCheck(dto);
        
        if(user == null) {//로그인 시도하려는 아이디가 존재하지 않으면..
        
/*        	response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
			out.flush();
			out.close();*/
        	mav.addObject("message","error");
			mav.setViewName("User/Login");
        }else {//로그인 시도하려는 아이디가 존재하면..
		
      //암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = user.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		
		//비밀번호 일치 시(로그인성공)
		if(loginResult) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", user.getName());
			
			String savePage = (String)session.getAttribute("savePage");
			
			if(savePage!=null) {
				mav.setViewName("redirect:/"+savePage);
				session.setAttribute("savePage", null);
				return mav;
			}
			mav.setViewName("redirect:/");
		}else {
			mav.addObject("message","error");
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
	@RequestMapping("member/viewPWD.do")
	public String viewuserPassword(@RequestParam String userid, Model model) {
		// 모델에 저장
		model.addAttribute("dto", memberService.viewMember(userid));
		// view.jsp에 포워딩
		return "User/RenewPWD";
	}
	
	@RequestMapping("member/updatememberinfo.do")
	public String updatememberinfo(MemberDTO dto, Model model,MemberDTO param,
			HttpSession session,HttpServletRequest request) {
			MemberDTO db = memberService.loginCheck(dto);
		//암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = db.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		
	if(db!=null) {
		if(loginResult) { //비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);
			//수정 후 목록으로 이동
			return "redirect:/";
		}else  { //비밀번호가 틀리면 
			model.addAttribute("dto", dto);
			model.addAttribute("join_date"
					,memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호가 틀립니다.");
			return "User/UpdateUser"; //forward
		}
	}
	return userHashedPasswd;
	}
	
	@ResponseBody
	@RequestMapping("member/update.do")
	public Map<String,String> updatememberpw(String beforePW, String newPW,HttpSession session) {
		Map<String,String> returnData = new HashMap<String,String>();
				
		String userid=(String)session.getAttribute("userid");
		String dbPW = memberService.getUserPW(userid);
		boolean result = passwordEncoder.matches(beforePW, dbPW);
		
		
		if(result) {//이전 암호가 일치하면
			Map<String,String> data = new HashMap<String,String>();
			data.put("userid", userid);
			data.put("newPW", passwordEncoder.encode(newPW));
			memberService.UpdateNewPassword(data);//비밀번호 업데이트
			
			returnData.put("code", "1");
			returnData.put("message", "비밀번호가 변경되었습니다 :)");
		}else {// 이전 암호와 일치하지 않으면
			returnData.put("code", "0");
			returnData.put("message", "비밀번호를 확인해주세요 :(");
		}
		
		return returnData;
		
	/*	MemberDTO db = memberService.loginCheck(dto);
		//암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = db.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		//비밀번호 체크
		boolean result=
memberService.checkPw(dto.getUserid(), dto.getPasswd());
		
	if(db!=null) {
		if(loginResult) { //비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);
			//수정 후 목록으로 이동
			return "redirect:/";
		}else  { //비밀번호가 틀리면 
			model.addAttribute("dto", dto);
			model.addAttribute("join_date"
					,memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "회원정보가 수정되었습니다.");
			return "User/UpdateUser"; //forward
		}
	}
	return userHashedPasswd;*/
}
	@RequestMapping("member/delete.do")
	public String delete(String userid,
			MemberDTO dto, Model model,HttpSession session) {
		/*boolean result=memberService.checkPw(userid, passwd);*/
		 //암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		   MemberDTO user = memberService.loginCheck(dto);
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = user.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		if(loginResult) { //비번이 맞으면 삭제 =>세션 끊고=> 목록으로 이동
			memberService.deleteMember(userid);
			session.invalidate();
			return "redirect:/";
		}else { //비번이 틀리면 되돌아감
			model.addAttribute("message","비밀번호를 확인하세요.");
			model.addAttribute(
					"dto", memberService.viewMember(userid));
			return "User/UpdateUser";
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
		
        boolean result=memberService.getUser(userid);
		return String.valueOf(result).trim();
        
	}
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;
	@ResponseBody
	@RequestMapping("member/sendpw.do")
	public Map<String,Object> sendEmailAction (MemberDTO dto) throws Exception {
		
		Map<String,Object> resultData = new HashMap<String,Object>();
		
		String userid=dto.getUserid();
		String u_email=dto.getEmail();
		
		
		//아이디, 이메일 일치 시..
		if(memberService.checkMemberByUserIdAndEmail(dto)==1) {//아이디, 이메일 일치확인
			String newPw=getNewPw();//새로 생성된 비밀번호10자리
			String encryptNewPassword=passwordEncoder.encode(newPw);
			dto.setPasswd(encryptNewPassword);
			
			if(memberService.MailUpdateUserPw(dto)==1) {//새로생성된 비밀번호 db 업데이트 성공 시..
				System.out.println(newPw);
				email.setContent("비밀번호는 "+newPw+" 입니다.");
				email.setReceiver(u_email);
				email.setSubject(userid+"님 비밀번호 찾기 메일입니다.");
				emailSender.SendEmail(email);
				
				resultData.put("code", "1");
				resultData.put("message", "새로운 임시 비밀번호가 이메일로 전송되었습니다.");
				
			}else {
				//여기는 혹시라도 db에 update가 실패 했을경우 처리코드..
			}
				
		}else {//아이디, 이메일 불 일치 시..
			resultData.put("code", "0");
			resultData.put("message", "아이디 또는 이메일을 확인해주세요.");
		
		}
		return resultData;
			
	}
	    
	//임시비밀번호 생성기
	public String getNewPw() {
		String pw = "";
	
		String tokenArray[] = {
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "O", "P", "R", "S", "T", "U", "X",
				"Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9"
		};
		for (int i = 0; i < 10; i++) {
			int idx = (int) (Math.random() * tokenArray.length);
			pw += (tokenArray[idx]);
		}
		return pw;
	}
	
	
}
