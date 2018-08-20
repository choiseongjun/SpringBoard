package jun.st.ex.Service;

import java.util.List;

import jun.st.ex.Persistence.DTO.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> list(int bno);//댓글 리스트
	public int count(int bno);//댓글 갯수
	public void create(ReplyDTO dto);//댓글 입력
	public void delete(Integer rno);
	public ReplyDTO detail(Integer rno);//상세보기
}
