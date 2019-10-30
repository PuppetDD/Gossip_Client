package model.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author GOLD
 */
public class MsgData {

    /**
     * 保存消息
     */
    public static Vector<Vector<String>> msg = new Vector<>();

    /**
     * 保存消息
     */
    public static Map<String, Vector<String>> MsgMap = new HashMap<>();

    /**
     * 保存好友账号
     */
    public static Vector<String> accountList = new Vector<>();

    /**
     * 保存消息提示
     */
    public static Map<String, Integer> msgTip = new HashMap<>();

}