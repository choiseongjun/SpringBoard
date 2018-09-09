package jun.st.ex.Persistence.DAO;

public interface ChatDAO {
	public int submit(String fromID,String toID,String chatContent);
}
