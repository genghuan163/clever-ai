package com.jjh.cleverai.ssh;//package com.jjh.cleverai.ssh;
//
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//import java.util.Properties;
//
//public class SshConnection {
//
////    private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/genghuanhuan/Documents/kaifa/login-hk.pem";
//    private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/genghuanhuan/Documents/kaifa/login-hk.pem";
//
//    // 自定义的中转接口，需要和数据源接口设置一样
//    private final static int LOCAl_PORT = 5555;
//    // 服务端的数据库端口
//    private final static int REMOTE_PORT = 3306;
//
//    // 服务器端SSH端口 默认是22
//    private final static int SSH_REMOTE_PORT = 22;
//    // SSH用户名
//    private final static String SSH_USER = "root";
//    // SSH使用密码
//    private final static String SSH_PASSWORD = "xxxxxxx";
//    // 连接到哪个服务端的SSH
//    private final static String SSH_REMOTE_SERVER = "43.198.102.61";
//    // 服务端的本地mysql服务
//    private final static String MYSQL_REMOTE_SERVER = "database-1.cu1gfeifb7ks.ap-east-1.rds.amazonaws.com";
//
//    private Session sesion; //represents each ssh session
//    // 测试连接
//    public static void main(String[] args) throws Throwable {
//        System.out.println(new SshConnection());
//    }
//    public SshConnection () throws Throwable
//    {
//
//        JSch jsch = new JSch();
//        // 需要用到了开启
//         jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
////        jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);
//
//        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
////        sesion.setPassword(SSH_PASSWORD);
//
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        sesion.setConfig(config);
//        // 去连接
//        sesion.connect(); //ssh connection established!
//        //  设置转发
//        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);
//
//        System.out.println("SSHConnection--运行OK");
//    }
//    public void closeSSH ()
//    {
//        sesion.disconnect();
//    }
//
//}