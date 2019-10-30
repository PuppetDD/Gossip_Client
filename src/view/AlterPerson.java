package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;

/**
 * @author GOLD
 * 修改个人信息窗口
 */
public class AlterPerson extends WindowStage {

    private ToggleGroup group;

    public AlterPerson() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/AlterPerson.fxml"));
        Scene scene = new Scene(root, 450, 800);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Gossip");
        group = new ToggleGroup();
        RadioButton radioButton = ((RadioButton) $("man"));
        radioButton.setToggleGroup(group);
        ((RadioButton) $("woman")).setToggleGroup(group);
        radioButton.setSelected(true);
        radioButton.requestFocus();
        move();
        quit();
        setIcon();
        cancel();
        minimiser();
    }

    @Override
    public void quit() {
        ((Button) $("quit1")).setTooltip(new Tooltip("关闭"));
        ((Button) $("quit1")).setOnAction(event -> {
            close();
        });
    }

    @Override
    public void minimiser() {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> {
            setIconified(true);
        });
    }

    public void cancel() {
        ((Button) $("cancel")).setOnAction(event -> {
            close();
        });
    }

    public void setErrorTip(String id, String text) {
        ((Label) $(id)).setText(text);
    }

    public void resetErrorTip() {
        ((Label) $("nameError")).setText("");
        ((Label) $("ageError")).setText("");
        ((Label) $("phoneError")).setText("");
    }

    public void setUserData(String id, String text) {
        if (id.equals("account")) {
            ((Label) $(id)).setText(text);
        } else if (id.equals("label")) {
            ((TextArea) $(id)).setText(text);
        } else {
            ((TextField) $(id)).setText(text);
        }
    }

    public void setUserData(Map<String, String> userMap) {
        ((Label) $("account")).setText(userMap.get("account"));
        ((TextField) $("name")).setText(userMap.get("name"));
        ((TextField) $("address")).setText(userMap.get("address"));
        ((TextField) $("age")).setText(userMap.get("age"));
        ((TextField) $("phone")).setText(userMap.get("phone"));
        ((TextArea) $("label")).setText(userMap.get("label"));
        if (userMap.get("sex").equals("man")) {
            ((RadioButton) $("man")).setSelected(true);
            ((RadioButton) $("man")).requestFocus();
        } else {
            ((RadioButton) $("woman")).setSelected(true);
            ((RadioButton) $("woman")).requestFocus();
        }
        setHeadPortrait(((Button) $("head")), userMap.get("head"));
        setHeadPortrait(((Button) $("background")), userMap.get("head"), "head1");
    }

    public static void setHeadPortrait(Button button, String head) {
        button.setStyle(String.format("-fx-background-image: url('/view/css/image/head/%s.jpg')", head));
    }

    public static void setHeadPortrait(Button button, String head, String file) {
        button.setStyle(String.format("-fx-background-image: url('/view/css/image/%s/%s.jpg')", file, head));
    }

}
