

import com.jcraft.jsch.*;
import java.io.*;

public class SSHExample 
{
public static void main(String[] args) 
{
    String user = "easol_user";
    String password = "";
    String host = "10.106.30.98";
    int port=22;

    String remoteFile="access.json";
    String privateKey = "D:/esl";

    

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
        //sftpChannel.cd("..");
		InputStream obj_InputStream = new ByteArrayInputStream("".getBytes());
	    sftpChannel.put(obj_InputStream,remoteFile);
        InputStream out= null;
        out= sftpChannel.get(remoteFile);
        OutputStream in=null;
        in=sftpChannel.put(remoteFile);
        BufferedWriter bw = new BufferedWriter(new PrintWriter(in));
        CreateJson cj=new CreateJson();
		bw.write(cj.create());
		bw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null) 
        {
            System.out.println(line);
        }
        
		br.close();
		sftpChannel.disconnect();
        session.disconnect();
    }
    catch(JSchException | SftpException | IOException e)
{
    System.out.println(e);
}
}
}