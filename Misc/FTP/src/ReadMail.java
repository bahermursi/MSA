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
public class ReadMail {
	public static void main(String args[]){
		String user = "easol_user";
		String password = "";
		String host = "10.106.30.98";
		int port = 22;

		//String remoteFile = "access.json";
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
			//createData(sftpChannel, sftpChannel1);
			//String path = "MSA/metadata";
			//getPath(sftpChannel, path);
			/*
			for (int i = 0; i < pathArray.size(); i++) {
				System.out.println(pathArray.get(i));
				// System.out.println(pathArray.get(i).split("/").length);
				client = pathArray.get(i).split("/");
				for (int j = 0; j < client.length; j++) {
					System.out.println(client[j]);
				}
				createFolderJson(sftpChannel1, pathArray.get(i));
			}
			*/
			readmail(sftpChannel,sftpChannel1);
			sftpChannel.disconnect();
			session.disconnect();
			sftpChannel1.disconnect();
			session1.disconnect();
		} catch (JSchException | SftpException e) {
			System.out.println(e);
		}
	}
	public static void readmail(ChannelSftp sftpChannel,ChannelSftp sftpChannel1) throws SftpException{
		Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*");
		int count=0;
		for (ChannelSftp.LsEntry li : list){
			
			if(li.getAttrs().isDir()){
				count++;
			}
			
		}
		if(count==0){
			ChannelSftp s = sftpChannel;
			ChannelSftp s1 = sftpChannel1;
			for (ChannelSftp.LsEntry li1 : list){
				System.out.println("Inside loop"+li1.getFilename());
				InputStream obj_InputStream = new ByteArrayInputStream("".getBytes());
				s1.put(obj_InputStream, li1.getFilename());
			    InputStream out=s.get(li1.getFilename());
			    s1.put(out,li1.getFilename());
				System.out.println("File "+li1.getFilename()+" succesfully copied");
			}
		}
		for (ChannelSftp.LsEntry oListItem : list) {
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
					readmail(s, s1);
					s.cd("..");
					s1.cd("..");
					}
				}
			}
		}
	}

}
