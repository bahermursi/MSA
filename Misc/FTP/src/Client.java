
public class Client {
	private String name;
	private String bcc;
	private long folderSize;
	private String lastUpdated;
	private int total_mails;
	private String imagePath;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public long getFolderSize() {
		return folderSize;
	}
	public void setFolderSize(long folderSize) {
		this.folderSize = folderSize;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public int getTotal_mails() {
		return total_mails;
	}
	public void setTotal_mails(int total_mails) {
		this.total_mails = total_mails;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
