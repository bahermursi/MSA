import com.jcraft.jsch.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.annotation.*;

public class Readfile {
	static List<String> pathArray = new ArrayList<String>();
	static String client[];

	public static void main(String args[]) throws IOException {
		String user = "easol_user";
		String password = "";
		String host = "10.106.30.98";
		int port = 22;

		String remoteFile = "access.json";
		String privateKey = "D:/esl";
		try {
			JSch jsch = new JSch();
			jsch.addIdentity(privateKey);
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Crating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			
			sftpChannel.connect();
			
			System.out.println("SFTP Channel created.");
			Session session1 = jsch.getSession(user, host, port);
			session1.setPassword(password);
			java.util.Properties config1 = new java.util.Properties();
			config1.put("StrictHostKeyChecking", "no");
			session1.setConfig(config1);
			session1.connect();
			ChannelSftp sftpChannel1 = (ChannelSftp) session1.openChannel("sftp");
			
			sftpChannel1.connect();
			sftpChannel.cd("MSA");
			
			sftpChannel1.cd("MSA/metadata");
			createData(sftpChannel, sftpChannel1);
			String path = "MSA/metadata";
			getPath(sftpChannel, path);
			for (int i = 0; i < pathArray.size(); i++) {
				System.out.println(pathArray.get(i));
				// System.out.println(pathArray.get(i).split("/").length);
				client = pathArray.get(i).split("/");
				for (int j = 0; j < client.length; j++) {
					System.out.println(client[j]);
				}
				createFolderJson(sftpChannel1, pathArray.get(i));
			}
			sftpChannel.disconnect();
			session.disconnect();
			sftpChannel1.disconnect();
			session1.disconnect();
		} catch (JSchException | SftpException e) {
			System.out.println(e);
		}
	}

	public static void createData(ChannelSftp sftpChannel, ChannelSftp sftpChannel1)
			throws SftpException, JSchException {
		Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*"); // List source
																// directory
																// structure.
		for (ChannelSftp.LsEntry oListItem : list) { // Iterate objects in the
														// list to get
														// file/folder names.
			ChannelSftp s = sftpChannel;
			ChannelSftp s1 = sftpChannel1;
			if (oListItem.getAttrs().isDir()) {

				// System.out.println(oListItem.getFilename());
				if (!oListItem.getFilename().equals("metadata")) {
					System.out.println(oListItem.getFilename());
					try{
						s1.cd(oListItem.getFilename());
					}
					catch(Exception e){
						s1.mkdir(oListItem.getFilename());
						s1.cd(oListItem.getFilename());
					}
					finally{
					s.cd(oListItem.getFilename());
					createData(s, s1);
					s.cd("..");
					s1.cd("..");
					}
				}
			}
		}

	}

	public static void getPath(ChannelSftp sftpChannel, String path) throws SftpException, JSchException {
		Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*"); // List source
																// directory
																// structure.
		if (list.size() < 1) {
			pathArray.add(path);
		}
		else{
			int count=0;
			for (ChannelSftp.LsEntry li : list){
				if(li.getAttrs().isDir()){
					count++;
				}
			}
			if(count==0){
				pathArray.add(path);
			}
		}
		for (ChannelSftp.LsEntry oListItem : list) { // Iterate objects in the
														// list to get
														// file/folder names.
			if (oListItem.getAttrs().isDir()) {

				// System.out.println(oListItem.getFilename());
				if (!oListItem.getFilename().equals("metadata")) {
					sftpChannel.cd(oListItem.getFilename());
					getPath(sftpChannel, path+ "/" + oListItem.getFilename());
					sftpChannel.cd("..");
				}
			}
		}

	}

	public static void createFolderJson(ChannelSftp sftpChannel, String path) throws SftpException, IOException {
		Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*");
		for (ChannelSftp.LsEntry oListItem : list) { // Iterate objects in the
														// list to get
														// file/folder names.
			if (oListItem.getAttrs().isDir()) {
				int flag=0;
				// System.out.println(client[2]);
				if (oListItem.getFilename().equals(client[2])) {
					sftpChannel.cd(client[2]);
					//attrs = sftpChannel.stat("MSA/metadata/"+client[2]+"/folder.json");
					Vector<ChannelSftp.LsEntry> fl=sftpChannel.ls("*");
					for(ChannelSftp.LsEntry file : fl){
						if(!file.getAttrs().isDir()){
							if(file.getFilename().equals("folder.json")){
								flag++;
							}
						}
					}
					
					
					
					if(flag!=0){
						//attrs = sftpChannel.stat("folder.json");
					} 
					else{
						InputStream obj_InputStream = new ByteArrayInputStream("".getBytes());
						sftpChannel.put(obj_InputStream, "folder.json");
						//int t=12;
					} 
					//finally {
						InputStream out = sftpChannel.get("folder.json");
						String theString = IOUtils.toString(out, "UTF-8");
						Folder f = new Folder();
						f.setPath(path);
						f.setClient(client[(client.length) - 1]);
						OutputStream in = sftpChannel.put("folder.json");
						BufferedWriter bw = new BufferedWriter(new PrintWriter(in));
						bw.write(getFolderJson(f, theString));
						bw.close();
						sftpChannel.cd("..");
					//}
				}

			}
		}
	}

	public static String getFolderJson(Folder f, String theString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		if (!theString.equals("")) {

			FolderRepository fr = mapper.readValue(theString, FolderRepository.class);
			List<Folder> folders = fr.getFolder();
			int flag=0;
			for(int i=0;i<folders.size();i++){
				if(folders.get(i).getPath().equals(f.getPath())){
					flag++;
				}
			}
			if(flag==0){
			folders.add(f);
			fr.setTokencount(fr.getTokencount() + 1);
			}
			return (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fr));
			
		}
		else {

			ObjectNode folder = mapper.createObjectNode();
			folder.put("name", f.getName());
			folder.put("path", f.getPath());

			ArrayNode folderlist = mapper.createArrayNode();
			folderlist.add(folder);

			ObjectNode folderTable = mapper.createObjectNode();
			folderTable.put("tokencount", "1");
			folderTable.putPOJO("folder",folderlist);

			return (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(folderTable));

		}
	}

}

