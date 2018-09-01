package jun.st.ex.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DTO.BoardDTO;
import jun.st.ex.Persistence.DTO.Pager;
import jun.st.ex.Service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	/*@RequestMapping("list.do") //세부적인 url pattern
	public ModelAndView list(
@RequestParam(defaultValue="name") String search_option,
@RequestParam(defaultValue="") String keyword,
@RequestParam(defaultValue="1") int curPage) 
						throws Exception{
		//레코드 갯수 계산
		int count=
				boardService.countArticle(search_option,keyword);
		//페이지 관련 설정
		Pager pager=new Pager(count, curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		
		List<BoardDTO> list=
boardService.listAll(search_option,keyword,start,end); //게시물 목록
		ModelAndView mav=new ModelAndView();
		HashMap<String,Object> map=new HashMap<>();
		map.put("list", list); //map에 자료 저장
		map.put("count", count);
		map.put("pager", pager); //페이지 네비게이션을 위한 변수
		map.put("search_option", search_option);
		map.put("keyword",keyword); 
		
		mav.setViewName("Board/list"); //포워딩할 뷰의 이름
		mav.addObject("map", map); //ModelAndView에 map을 저장
		return mav; // board/list.jsp로 이동
	}*/
	
	@RequestMapping("list.do") //세부적인 url pattern
	public ModelAndView list() throws Exception{
		ModelAndView mav=new ModelAndView();
		//레코드 갯수 계산
		int count=boardService.countArticle("name","");
		HashMap<String,Object> map=new HashMap<>();
		map.put("count", count);
		mav.addObject("map", map); //ModelAndView에 map을 저장
		mav.setViewName("Board/list"); //포워딩할 뷰의 이름
		
		return mav; // board/list.jsp로 이동
	}
	

	@RequestMapping("ajaxList.do") //세부적인 url pattern
	public @ResponseBody HashMap ajaxList(
@RequestParam(defaultValue="name") String search_option,
@RequestParam(defaultValue="") String keyword,
@RequestParam(defaultValue="1") int curPage) 
						throws Exception{
		//레코드 갯수 계산
		int count=
				boardService.countArticle(search_option,keyword);
		//페이지 관련 설정
		Pager pager=new Pager(count, curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		
		List<BoardDTO> list=
boardService.listAll(search_option,keyword,start,end); //게시물 목록
		ModelAndView mav=new ModelAndView();
		HashMap<String,Object> map=new HashMap<>();
		map.put("list", list); //map에 자료 저장
		map.put("count", count);
		map.put("pager", pager); //페이지 네비게이션을 위한 변수
		map.put("search_option", search_option);
		map.put("keyword",keyword); 
		
		return map; // board/list.jsp로 이동
	}
	
	
	@RequestMapping("delete.do")
	public String delete(int bno) throws Exception {
		boardService.delete(bno); //삭제 처리
		return "redirect:/board/list.do"; //목록으로 이동
	}
	
//게시물 내용 수정	
	@RequestMapping("update.do")
	public String update(BoardDTO dto) throws Exception {
		System.out.println("dto:"+dto);
		if(dto != null) {
			boardService.update(dto); //레코드 수정
		}
		// 수정 완료 후 목록으로 이동
		//return "redirect:/board/list.do";
		// 상세 화면으로 되돌아감
		/*return "redirect:/board/list.do";*/
		return "redirect:/board/view.do?bno="+dto.getBno();
	}
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto
			, HttpSession session)
		throws Exception {
		// 세션에서 사용자아이디를 가져옴
		String writer=(String)session.getAttribute("userid");
		String adminwriter=(String)session.getAttribute("admin_userid");
		dto.setWriter(writer);
		/*dto.setWriter(adminwriter);*/
		//레코드 저장
		boardService.create(dto);
		//게시물 목록으로 이동
		return "redirect:/board/list.do";
	}
	@RequestMapping("write.do")
	public String write() {
		// 글쓰기 폼 페이지로 이동
		return "Board/write";
	}
	@RequestMapping("view.do")
	public ModelAndView view(int bno, HttpSession session)
		throws Exception {
		//조회수 증가 처리 
		boardService.increaseViewcnt(bno, session); 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Board/view"); //포워딩할 뷰의 이름
		mav.addObject("dto", boardService.read(bno)); //자료 저장
		return mav; //  views/board/view.jsp로 넘어가서 출력됨
	} 
	@RequestMapping("getAttach/{bno}")
	@ResponseBody // view가 아닌 데이터 자체를 리턴 
	public List<String> getAttach(@PathVariable int bno){
		return boardService.getAttach(bno);
	}
	@RequestMapping(value="/reply.b",method=RequestMethod.GET)
	public String replyForm(int ref,int ref_group,Model model) {
		//BoardDTO board = boardService.replyForm(num);이거 필요 없음
		model.addAttribute("ref",ref);
		model.addAttribute("ref_group",ref_group);
		return "Board/replyForm";
	}
	@RequestMapping(value="/replyInsert.b",method=RequestMethod.POST)
	public String replyForm(BoardDTO dto, HttpSession session) {
		//boardService.replyUpdate(dto);//나중에 서비스부터 해서 다 날려버릴 것..
		
		String writer=(String)session.getAttribute("userid");
		dto.setWriter(writer);
		boardService.replyInsert(dto);
		return "redirect:/board/list.do";
	}
}
