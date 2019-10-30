package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author GOLD
 * 警告窗口
 */
public class Alert extends WindowStage {

    private boolean judge;

    public Alert() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/Alert.fxml"));
        Scene scene = new Scene(root, 500, 250);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Gossip");
        judge = false;
        move();
        quit();
        setIcon();
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

    public boolean exec() {
        show();
        judge = false;
        ((Button) $("submit")).setOnAction(event -> {
            judge = true;
            close();
        });
        ((Button) $("cancel")).setOnAction(event -> {
            judge = false;
            close();
        });
        return judge;
    }

    public void setInformation(String Text) {
        ((Label) $("information")).setText(Text);
    }

    public void setModality(WindowStage Own) {
        initModality(Modality.APPLICATION_MODAL);
        initOwner(Own);
    }

}
