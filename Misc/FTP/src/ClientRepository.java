import java.util.List;

public class ClientRepository {
	private int tokencount;
	private List<Client> client;
	public int getTokencount() {
		return tokencount;
	}
	public void setTokencount(int tokencount) {
		this.tokencount = tokencount;
	}
	public List<Client> getClient() {
		return client;
	}
	public void setClient(List<Client> client) {
		this.client = client;
	}
}
