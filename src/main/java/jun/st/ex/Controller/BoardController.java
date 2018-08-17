package jun.st.ex.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DTO.BoardDTO;
import jun.st.ex.Service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception{
		List<BoardDTO> list=boardService.listAll();
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("Board/BoardList");
		mav.addObject("list",list);
		return mav;
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
		return "redirect:/board/view.do?bno="+dto.getBno();
	}
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto
			, HttpSession session)
		throws Exception {
		// 세션에서 사용자아이디를 가져옴
		String writer=(String)session.getAttribute("userid");
		dto.setWriter(writer); 
		//레코드 저장
		boardService.create(dto);
		//게시물 목록으로 이동
		return "redirect:/board/list.do";
	}
	
}
