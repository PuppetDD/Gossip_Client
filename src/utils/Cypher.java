package utils;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;

/**
 * Gossip_Client
 * utils
 *
 * @author GOLD
 * @date 2019/10/27
 */
public class Cypher {

    private static String code = "UTF-8";
    private static String classPath=Cypher.class.getResource("").getPath();
    private static String path = classPath+"resource/Cipher";
    private static File file = new File(path);
    private static byte[] buffer = new byte[1024];

    static {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            in.read(buffer, 0, 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(String text) {
        String result=null;
        try {
            byte[] cache = text.getBytes(code);
            int len=cache.length;
            int max= buffer.length-len;
            int begin=new Random().nextInt(max);
            for (int i = 0,j=begin; i < len; i++,j++) {
                cache[i] = (byte) (cache[i] ^ buffer[j]);
            }
            String str=String.valueOf(new BigInteger(cache));
            result=begin+":"+str;
            System.out.println("\nEnCode");
            System.out.println("begin:"+begin);
            System.out.println("Length:"+len);
            System.out.println("Result:\n"+result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decode(String target) {
        String result=null;
        try {
            String[] str=target.split(":");
            int begin=Integer.parseInt(str[0]);
            byte[] cache = new BigInteger(str[1]).toByteArray();
            int len=cache.length;
            for (int i = 0,j=begin; i < len; i++,j++) {
                cache[i] = (byte) (cache[i] ^ buffer[j]);
            }
            result= new String(cache, code);
            System.out.println("\nDeCode");
            System.out.println("Target:\n"+target);
            System.out.println("Split target:\n"+str[1]);
            System.out.println("begin:"+begin);
            System.out.println("Length:"+len);
            System.out.println("Result:");
            System.out.print(result+"DeCode End");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
