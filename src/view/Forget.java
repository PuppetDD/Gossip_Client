package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author GOLD
 * 忘记密码
 */
public class Forget extends WindowStage {

    public Forget() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/Forget.fxml"));
        Scene scene = new Scene(root, 700, 450);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Gossip");
        move();
        quit();
        setIcon();
        minimiser();
    }

    @Override
    public void quit() {
        ((Button) $("quit1")).setTooltip(new Tooltip("退出"));
        ((Button) $("quit1")).setOnAction(event -> {
            close();
            System.exit(0);
        });
    }

    @Override
    public void minimiser() {
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
        ((Button) $("minimiser1")).setOnAction(event -> {
            setIconified(true);
        });
    }

    public void setErrorTip(String id, String text) {
        ((Label) $(id)).setText(text);
    }

    public void resetErrorTip() {
        ((Label) $("accountError")).setText("");
        ((Label) $("nameError")).setText("");
        ((Label) $("phoneError")).setText("");
        ((Label) $("passwordError")).setText("");
        ((Label) $("rePasswordError")).setText("");
    }

    public void clear() {
        ((TextField) $("account")).clear();
        ((TextField) $("name")).clear();
        ((TextField) $("phone")).clear();
        ((PasswordField) $("password")).clear();
        ((PasswordField) $("rePassword")).clear();
    }

}
