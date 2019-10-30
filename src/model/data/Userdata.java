package model.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GOLD
 * 管理用户数据的辅助类
 */
public class Userdata {

    /**
     * 保存用户数据
     */
    private Map<String, String> userMap;

    public Userdata(String Username, String Password, String account, String label, String sex, String address, String phone, String head, int age, String background) {
        userMap = new HashMap<>();
        userMap.put("account", account);
        userMap.put("password", Password);
        userMap.put("label", label);
        userMap.put("sex", sex);
        userMap.put("head", head);
        userMap.put("age", String.valueOf(age));
        userMap.put("address", address);
        userMap.put("phone", phone);
        userMap.put("name", Username);
        userMap.put("background", background);
    }

    public Userdata(ResultSet resultSet) throws SQLException {
        userMap = new HashMap<>();
        userMap.put("account", resultSet.getString("account"));
        userMap.put("password", resultSet.getString("password"));
        userMap.put("label", resultSet.getString("label"));
        userMap.put("sex", resultSet.getString("sex"));
        userMap.put("head", resultSet.getString("head"));
        userMap.put("age", resultSet.getString("age"));
        userMap.put("address", resultSet.getString("address"));
        userMap.put("phone", resultSet.getString("phone"));
        userMap.put("name", resultSet.getString("name"));
        userMap.put("background", resultSet.getString("background"));
    }

    public Userdata() {
        userMap = new HashMap<>();
    }

    public void setUserdata(ResultSet resultSet) throws SQLException {
        userMap.put("account", resultSet.getString("account"));
        userMap.put("password", resultSet.getString("password"));
        userMap.put("label", resultSet.getString("label"));
        userMap.put("sex", resultSet.getString("sex"));
        userMap.put("head", resultSet.getString("head"));
        userMap.put("age", resultSet.getString("age"));
        userMap.put("address", resultSet.getString("address"));
        userMap.put("phone", resultSet.getString("phone"));
        userMap.put("name", resultSet.getString("name"));
        userMap.put("background", resultSet.getString("background"));
    }

    public Map<String, String> getUserdata() {
        return userMap;
    }

    public void setAccount(String account) {
        userMap.put("account", account);
    }

    public String getAccount() {
        return userMap.get("account");
    }

    public void setName(String name) {
        userMap.put("name", name);

    }

    public String getName() {
        return userMap.get("name");
    }

    public void setPassword(String password) {
        userMap.put("password", password);
    }

    public String getPassword() {
        return userMap.get("password");
    }

    public void setAge(int age) {
        userMap.put("age", String.valueOf(age));
    }

    public String getAge() {
        return userMap.get("age");
    }

    public void setSex(String sex) {
        userMap.put("sex", sex);
    }

    public String getSex() {
        return userMap.get("sex");
    }

    public void setHead(String head) {
        userMap.put("head", head);
    }

    public String getHead() {
        if (userMap.get("head") == null) {
            userMap.put("head", "head1");
        }
        return userMap.get("head");
    }

    public void setAddress(String address) {
        userMap.put("address", address);
    }

    public String getAddress() {
        return userMap.get("address");
    }

    public void setLabel(String label) {
        userMap.put("label", label);
    }

    public String getLabel() {
        return userMap.get("label");
    }

    public void setPhone(String phone) {
        userMap.put("phone", phone);
    }

    public String getPhone() {
        return userMap.get("phone");
    }

    public void setData(ResultSet resultSet) throws SQLException {
        setName(resultSet.getString("name"));
        setPassword(resultSet.getString("password"));
        setAccount(resultSet.getString("account"));
        setAddress(resultSet.getString("address"));
        setAge(Integer.parseInt(resultSet.getString("age")));
        setHead(resultSet.getString("head"));
        setSex(resultSet.getString("sex"));
        setLabel(resultSet.getString("label"));
        setPhone(resultSet.getString("phone"));
    }

    public String getBackground() {
        return userMap.get("background");
    }

}
