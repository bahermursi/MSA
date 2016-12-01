package com.listerdigital.MSA.file;
import java.util.List;

import com.listerdigital.MSA.domain.*;
import com.listerdigital.MSA.repository.*;
import com.listerdigital.MSA.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import java.io.*;

import org.apache.commons.io.IOUtils;
import java.sql.*;

public class ClientFile {
	String user = "";
    String password = "";
    String host = "";
    int port=22;
    String theString="";
    String privateKey = "";
    List<Client> clientList;
	public List<Client> getallClients(){
		try{
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@10.106.20.63:1521:dexter","training5","training5");
		    PreparedStatement ps=con.prepareStatement("Select * from sshcredentials_msa");
		    ResultSet rs=ps.executeQuery();
		    while(rs.next()){
		    	user=rs.getString(1);
		    	host=rs.getString(2);
		    	port=Integer.parseInt(rs.getString(3));
		    	privateKey=rs.getString(4);
		    }
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		try
	    {
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
	        //ChannelSftp sftpChannel2 = (ChannelSftp) session.openChannel("sftp");
	        sftpChannel.connect();
	        //sftpChannel2.connect();
	        System.out.println("SFTP Channel created.");
	        
	        /*sftpChannel.cd("Souvik/newfolder");
	        File f=new File(remoteFile);
	        sftpChannel.put(new FileInputStream(f), f.getName());*/
	        //sftpChannel.mkdir("MSA");
	        //sftpChannel.mkdir("MSA/metadata");
	        sftpChannel.cd("MSA/metadata");
			//InputStream obj_InputStream = new ByteArrayInputStream("".getBytes());
		    //sftpChannel.put(obj_InputStream,remoteFile);
	        InputStream out= null;
	        out= sftpChannel.get("client.json");
	        theString = IOUtils.toString(out,"UTF-8");
	        ObjectMapper mapper=new ObjectMapper();
	        ClientRepository cr=mapper.readValue(theString, ClientRepository.class);
	        for(int i=0;i<cr.getTokencount();i++){
	        	new ClientService(cr.getClient().get(i));
	        }
	        cr=mapper.readValue(theString, ClientRepository.class);
	        clientList=cr.getClient();
	        //System.out.println("String succesfully converted:"+theString);
			sftpChannel.disconnect();
	        session.disconnect();
	    }
	    catch(JSchException | SftpException | IOException e)
		{
		    System.out.println(e);
		}
		return clientList;
	}
}
