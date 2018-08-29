package jun.st.ex.Persistence.DAO;

import java.util.List;

import jun.st.ex.Persistence.DTO.ReplyDTO;

public interface ReplyDao {
	public List<ReplyDTO> list(int bno);
	public int count(int bno);
	public void create(ReplyDTO dto);
	public void delete(Integer rno);
	public ReplyDTO detail(Integer rno);//상세보기
	public void update(ReplyDTO vo);
}
