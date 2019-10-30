package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author GOLD
 * 选择头像页面
 */
public class HeadPortrait extends WindowStage {

    private ToggleGroup group;

    public HeadPortrait() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/HeadPortrait.fxml"));
        Scene scene = new Scene(root, 700, 440);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        group = new ToggleGroup();
        group();
        setResizable(false);
        setTitle("Gossip");
        move();
        setIcon();
        quit();
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

    public void group() {
        ((RadioButton) $("one")).setToggleGroup(group);
        ((RadioButton) $("two")).setToggleGroup(group);
        ((RadioButton) $("three")).setToggleGroup(group);
        ((RadioButton) $("four")).setToggleGroup(group);
        ((RadioButton) $("five")).setToggleGroup(group);
        ((RadioButton) $("six")).setToggleGroup(group);
        ((RadioButton) $("seven")).setToggleGroup(group);
        ((RadioButton) $("eight")).setToggleGroup(group);
        ((RadioButton) $("nine")).setToggleGroup(group);
        ((RadioButton) $("ten")).setToggleGroup(group);
        ((RadioButton) $("one")).setSelected(true);
        ((RadioButton) $("one")).requestFocus();
    }

    public void setModality(WindowStage Own) {
        initModality(Modality.APPLICATION_MODAL);
        initOwner(Own);
    }

}
