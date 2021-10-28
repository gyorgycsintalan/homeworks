package utility;

import java.io.*;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import properties.ConfigProperties;

public class Utility {
    public static void writeToFile(String data, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(data);

        writer.close();
    }

    public static void writeToFile(List<String> data, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (String line : data) {
            writer.write(line+'\n');
        }

        writer.close();
    }

    public static void sendFileThroughFtp(String filename) throws IOException {
        ConfigProperties configProperties = new ConfigProperties();
        String ftpAddress= configProperties.readProperty("ftp_address");
        Integer ftpPort= Integer.valueOf(configProperties.readProperty("ftp_port"));
        String ftpUser= configProperties.readProperty("ftp_user");
        String ftpPassword= configProperties.readProperty("ftp_password");

        FTPClient ftpClient = new FTPClient();

        ftpClient.connect(ftpAddress, ftpPort);
        ftpClient.login(ftpUser, ftpPassword);
        ftpClient.enterLocalPassiveMode();

        File localFile = new File(System.getProperty("user.dir") + "\\" + filename);

        String remoteFile = filename;
        InputStream inputStream = new FileInputStream(localFile);

        boolean done = ftpClient.storeFile(filename, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("The file upload was successful.");
        }

        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}
