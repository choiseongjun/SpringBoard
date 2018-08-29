package jun.st.ex.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jun.st.ex.Persistence.DTO.ReplyDTO;
import jun.st.ex.Service.ReplyService;


// @ResponseBody를 붙이지 않아도 뷰가 아닌 데이터 리턴 가능
@RestController // spring 4.0부터 사용 가능
@RequestMapping("reply/*") //공통적인 url pattern
public class ReplyController {

	@Inject
	ReplyService replyService;
	
	@RequestMapping("list.do")
	public ModelAndView list(int bno, ModelAndView mav) {
		List<ReplyDTO> list=replyService.list(bno); //댓글 목록
		mav.setViewName("Board/reply_list"); //뷰의 이름
		mav.addObject("list", list); //뷰에 전달할 데이터 저장
		return mav; //뷰로 이동
	} 
	//댓글 목록을 ArrayList로 리턴
	@RequestMapping("list_json.do")
	public List<ReplyDTO> list_json(int bno){
		return replyService.list(bno);
	}
	
	@RequestMapping("insert.do") //세부적인 url pattern
	public void insert(ReplyDTO dto, HttpSession session) {
		//댓글 작성자 아이디
		String userid=(String)session.getAttribute("userid");
		dto.setReplyer(userid);
		//댓글이 테이블에 저장됨
		replyService.create(dto);
		//jsp 페이지로 가거나 데이터를 리턴하지 않음
	}
	@RequestMapping("delete/{rno}")
	public ResponseEntity<String> replyDelete(@PathVariable("rno") Integer rno){
		ResponseEntity<String> entity=null;
		
		try {
			replyService.delete(rno);
			entity=new ResponseEntity<String>("success",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/detail/{rno}",method=RequestMethod.GET)
	public ModelAndView replyDetail(@PathVariable("rno") Integer rno,ModelAndView mav) {
		ReplyDTO vo=replyService.detail(rno);
		mav.setViewName("Board/replyDetail");
		mav.addObject("vo",vo);
		return mav;
	}
	@RequestMapping(value="/update/{rno}",method= {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("rno")Integer rno,@RequestBody ReplyDTO vo) {
		ResponseEntity<String> entity=null;
		try {
			vo.setRno(rno);
			replyService.update(vo);
			entity=new ResponseEntity<String>("success",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
}


