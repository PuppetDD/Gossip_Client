package model;

import controller.Controller;
import javafx.application.Platform;
import model.data.MsgData;
import utils.Cypher;
import view.MainWindow;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author GOLD
 * 接收信息类 多线程接收服务端的信息
 */
public class ChatManager {

    private String ip = "127.0.0.1";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainWindow mainWindow;
    private String account;

    private ChatManager() {

    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
        return instance;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * 链接服务器
     *
     * @param ip
     * @param account
     */
    public void connect(String ip, String account) {
        this.ip = ip;
        this.account = account;
        new Lister().start();
    }

    public void send(String msg) throws IOException {//发送消息  向服务器发送
        if (out != null) {
            out.writeUTF(msg);
            out.flush();
        } else {
            Controller.alert.setInformation("发送失败!");
            Controller.alert.exec();
        }
    }

    /**
     * 连接服务器
     */
    class Lister extends Thread {

        @Override
        public void run() {
            try {
                socket = new Socket(ip, 9999);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(account);
                String line;
                while ((line = in.readUTF()) != null) {
                    System.out.println("Accept:" + line);
                    String[] str = line.split(" ");
                    String I_account = str[0];
                    String Y_account = str[1];
                    String Msg = "";
                    for (int i = 2; i < str.length; i++) {
                        Msg += str[i] + " ";
                    }
                    System.out.println("Msg:" + Msg);
                    //获取信息格式
                    try {
                        //别人把你添加为好友的信息
                        if (Msg.equals("###@ ")) {
                            System.out.println("加好友请求");
                            ResultSet set = Controller.database.execResult("SELECT head FROM user WHERE account = ?", I_account);
                            set.next();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        mainWindow.addFriend(set.getString("head"), I_account, I_account, Controller.database, Controller.friendPage);
                                        MsgData.accountList.add(I_account);
                                        MsgData.msg.add(new Vector<>());
                                        mainWindow.getFriendVector().get(MsgData.accountList.size() - 1).setOnline();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            if (((Label) mainWindow.$("Y_account")).getText().equals("Gossip聊天助手")) {
                                String msg = I_account + "把你添加为好友,和他聊天吧";
                                mainWindow.addLeft("system", msg);
                                MsgData.msg.get(0).add("Gossip聊天助手 " + msg);
                            } else {
                                String msg = I_account + "把你添加为好友,和他聊天吧";
                                MsgData.msg.get(0).add("Gossip聊天助手 " + msg);
                                int cnt = MsgData.msgTip.get("Gossip聊天助手");
                                cnt++;
                                MsgData.msgTip.put("Gossip聊天助手", cnt);
                                mainWindow.getFriendVector().get(0).addMsgTip(cnt);
                            }
                            MsgData.msgTip.put(I_account, 0);
                        } else if (Msg.equals("##@@ ")) {
                            //别人把你删除的信息
                            System.out.println("删好友请求");
                            if (((Label) mainWindow.$("Y_account")).getText().equals("Gossip聊天助手")) {
                                String msg = I_account + "已把你删除";
                                mainWindow.addLeft("system", msg);
                                MsgData.msg.get(0).add("Gossip聊天助手 " + msg);
                                int index = MsgData.accountList.indexOf(I_account);
                                if (index != -1) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            mainWindow.getFriendVector().remove(index);
                                            ((ListView) mainWindow.$("FriendList")).getItems().remove(index);
                                            MsgData.accountList.remove(index);
                                            MsgData.msg.remove(index);
                                            MsgData.MsgMap.remove(I_account);
                                        }
                                    });
                                }
                            } else {
                                String msg = I_account + "已把你删除";
                                MsgData.msg.get(0).add("Gossip聊天助手 " + msg);
                                int cnt = MsgData.msgTip.get("Gossip聊天助手");
                                cnt++;
                                MsgData.msgTip.put("Gossip聊天助手", cnt);
                                mainWindow.getFriendVector().get(0).addMsgTip(cnt);
                                if (((Label) mainWindow.$("Y_account")).getText().equals(I_account)) {
                                    mainWindow.getFriendList().getSelectionModel().select(0);
                                }
                                int i = MsgData.accountList.indexOf(I_account);
                                if (i != -1) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            mainWindow.getFriendVector().remove(i);
                                            ((ListView) mainWindow.$("FriendList")).getItems().remove(i);
                                            MsgData.accountList.remove(i);
                                            MsgData.msg.remove(i);
                                            MsgData.MsgMap.remove(I_account);
                                        }
                                    });
                                }
                            }
                        } else if (Msg.equals("#@@@ ")) {
                            //有用户上线的信息
                            System.out.println("用户上线");
                            int i = MsgData.accountList.indexOf(I_account);
                            if (i != -1) {
                                mainWindow.getFriendVector().get(i).setOnline();
                            }
                        } else if (Msg.equals("@@@@ ")) {
                            //用户下线信息
                            System.out.println("用户下线");
                            int i = MsgData.accountList.indexOf(I_account);
                            if (i != -1) {
                                mainWindow.getFriendVector().get(i).setOutline();
                            }
                        } else {//一般信息
                            //解密内容
                            System.out.println("用户消息");
                            System.out.println(Msg);
                            Msg = Cypher.decode(Msg.trim());
                            if (MsgData.accountList.get(mainWindow.getFriendList().getSelectionModel().getSelectedIndex()).equals(I_account)) {
                                ResultSet resultSet = Controller.database.execResult("SELECT head FROM user WHERE account = ?", I_account);
                                resultSet.next();
                                mainWindow.addLeft(resultSet.getString("head"), Msg);
                                int i = MsgData.accountList.indexOf(I_account);
                                if (i != -1) {
                                    MsgData.msg.get(i).add(I_account + " " + Msg);
                                }
                            } else {
                                int i = MsgData.accountList.indexOf(I_account);
                                if (i != -1) {
                                    MsgData.msg.get(i).add(I_account + " " + Msg);
                                    int cnt = MsgData.msgTip.get(I_account);
                                    cnt++;
                                    MsgData.msgTip.put(I_account, cnt);
                                    mainWindow.getFriendVector().get(i).addMsgTip(cnt);
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                in.close();
                out.close();
                in = null;
                out = null;
            } catch (IOException e) {
                System.out.println("退出");
            }
        }

    }

}
