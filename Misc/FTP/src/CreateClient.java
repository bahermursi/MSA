import com.jcraft.jsch.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
public class CreateClient {
	public static int mailcount;
	public static void main(String args[]) throws IOException{
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
			Client c1=new Client();
			c1.setName("Flipkart");
			c1.setBcc(c1.getName().toLowerCase()+"@listerdigital.com");
			c1.setImagePath("resources/images/"+c1.getName()+".jpg");
			create(sftpChannel,sftpChannel1,c1);
			sftpChannel.disconnect();
			session.disconnect();
			sftpChannel1.disconnect();
			session1.disconnect();
		} catch (JSchException | SftpException e) {
			System.out.println(e);
		}
	}
	public static void create(ChannelSftp sftpChannel,ChannelSftp sftpChannel1,Client cl) throws SftpException, IOException{
		mailcount=0;
		cl.setTotal_mails(getMails(sftpChannel,cl.getName()));
		try{
		cl.setLastUpdated(sftpChannel.lstat(cl.getName()).getMtimeString());
		}
		catch(Exception e){
			Date d=new Date();
			cl.setLastUpdated(d.toString());
		}
		try{
		cl.setFolderSize(sftpChannel.lstat(cl.getName()).getSize());
		}
		catch(Exception e){
			cl.setFolderSize(0);
		}
		setJson(sftpChannel,sftpChannel1,cl,cl.getName());
	}
	public static int getMails(ChannelSftp sftpChannel,String fname) throws SftpException{
		Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*");
		for (ChannelSftp.LsEntry oListItem : list) {
			if (oListItem.getAttrs().isDir()) {
			if (oListItem.getFilename().equals(fname)){ //Key Factor
				sftpChannel.cd(fname);
				System.out.println("Inside "+fname);
				Vector<ChannelSftp.LsEntry> list1 = sftpChannel.ls("*");
				int count=0;
				for(ChannelSftp.LsEntry file : list1){
					if(file.getAttrs().isDir()){
						count++;
					}
				}
				if(count==0){
					//System.out.println("Inside count");
					for(ChannelSftp.LsEntry file1 : list1){
						if(!file1.getAttrs().isDir()){
							mailcount++;
						}
					}
				}
				
				for(ChannelSftp.LsEntry file:list1){
			    if(file.getAttrs().isDir()){
				 getMails(sftpChannel,file.getFilename());
			    }
				}
				sftpChannel.cd("..");
				}
				//sftpChannel.cd("..");
			}
			
		}
		return mailcount;
	}
	public static void setJson(ChannelSftp sftpChannel,ChannelSftp sftpChannel1,Client cl,String fname) throws SftpException, IOException{
		//Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*");
		/*
		for (ChannelSftp.LsEntry oListItem : list) {
			if (oListItem.getAttrs().isDir()) {
			if (oListItem.getFilename().equals(fname)){ //Key Factor
				
				
		*/
		//sftpChannel.cd(fname);
				/*
				try{
					sftpChannel1.cd(fname);
				}
				catch(Exception e){
					sftpChannel1.mkdir(fname);
					sftpChannel1.cd(fname);
				}*/
				
				
				/*
				System.out.println("Inside "+fname);
				Vector<ChannelSftp.LsEntry> list1 = sftpChannel.ls("*");
				int count=0;
				for(ChannelSftp.LsEntry file : list1){
					if(file.getAttrs().isDir()){
						count++;
					}
				}*/
				//if(count==0){
					int flag=0;
					Vector<ChannelSftp.LsEntry> fl=sftpChannel1.ls("*");
					for(ChannelSftp.LsEntry file : fl){
						if(!file.getAttrs().isDir()){
							if(file.getFilename().equals("client.json")){
								flag++;
							}
						}
					}
					
					if(flag!=0){
						//attrs = sftpChannel.stat("folder.json");
					} 
					else{
						InputStream obj_InputStream = new ByteArrayInputStream("".getBytes());
						sftpChannel1.put(obj_InputStream, "client.json");
						//int t=12;
					} 
					//finally {
						InputStream out = sftpChannel1.get("client.json");
						String theString = IOUtils.toString(out, "UTF-8");
						OutputStream in = sftpChannel1.put("client.json");
						BufferedWriter bw = new BufferedWriter(new PrintWriter(in));
						bw.write(getClientJson(cl, theString));
						bw.close();
						sftpChannel.cd("..");
					//}
				//}
				
				/*
				for(ChannelSftp.LsEntry file:list1){
			    if(file.getAttrs().isDir()){	
				 setJson(sftpChannel,sftpChannel1,cl,file.getFilename());
			    }
				}*/
						//sftpChannel.cd("..");
						//sftpChannel1.cd("..");
		/*				
					}
				}//sftpChannel.cd("..");
			}
			
		*/	
	}
	public static String getClientJson(Client cl,String theString) throws IOException{
		ObjectMapper mapper = new ObjectMapper();

		if (!theString.equals("")) {

			ClientRepository cr = mapper.readValue(theString, ClientRepository.class);
			List<Client> clients = cr.getClient();
			int flag=0;
			for(int i=0;i<clients.size();i++){
				if(clients.get(i).getName().equals(cl.getName())){
					clients.remove(i);
					clients.add(cl);
					flag++;
				}
			}
			if(flag==0){
			clients.add(cl);
			cr.setTokencount(cr.getTokencount() + 1);
			}
			return (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cr));
			
		}
		else {

			ObjectNode client = mapper.createObjectNode();
			client.put("name", cl.getName());
			client.put("bcc", cl.getBcc());
			client.put("folderSize", cl.getFolderSize());
			client.put("lastUpdated", cl.getLastUpdated());
			client.put("total_mails", cl.getTotal_mails());
			client.put("imagePath", cl.getImagePath());

			ArrayNode clientlist = mapper.createArrayNode();
			clientlist.add(client);

			ObjectNode clientTable = mapper.createObjectNode();
			clientTable.put("tokencount", "1");
			clientTable.putPOJO("client",clientlist);

			return (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientTable));

		}
	}
}
