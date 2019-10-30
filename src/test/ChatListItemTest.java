package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Tool;
import view.MainWindow;

/**
 * Gossip_Client
 * test
 *
 * @author GOLD
 * @date 2019/10/29
 */
public class ChatListItemTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String head="system";
//        String msg="不要想怎么多，我只是拿来测试的，你以为什么？\n"
//                +"是你想多了吧，谁会管你是拿来干什么的，你爱干嘛干嘛，关我什么事\n"
//                +"不是啦，其实是想你来帮忙的，别误会";
        String msg="321";
        double width= Tool.getWidth(msg);
        double height=Tool.getHeight(msg);
        ChatListItem chatItem=new ChatListItem();
        Pane chat=chatItem.left(head,msg,width,height);
        //Pane chat=chatItem.right(head,msg,width,height);
        Scene scene = new Scene(chat, 1200, 600);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ChatListItem {

        private Pane pane;
        private Pane left;
        private Pane right;
        private Button head;
        private TextArea text;
        private Button arrow;

        public ChatListItem() {
            pane = new Pane();
            left = new Pane();
            right = new Pane();
            head = new Button();
            text = new TextArea();
            arrow = new Button();
            pane.setPrefSize(730, 150);
            left.setPrefSize(580, 70);
            right.setPrefSize(580, 70);
            head.setPrefSize(50, 50);
            //text.setPrefSize(480, 50);
            arrow.setPrefSize(32, 32);
            pane.getStyleClass().add("pane");
            left.getStyleClass().add("pane");
            right.getStyleClass().add("pane");
            head.getStyleClass().add("head");
            //自动换行
            text.setWrapText(true);
            text.setEditable(false);
            arrow.setDisable(false);
        }

        public Pane left(String iHead, String iText, double width, double height) {//别人的消息
            pane.setPrefHeight(110 + height);
            head.setLayoutY(10);
            head.setLayoutX(10);
            left.setPrefHeight(height);
            text.getStyleClass().add("leftText");
            //text.setPrefSize(width, height);
            text.setMinSize(width,height);
            text.setMaxSize(width,height);
            text.setLayoutX(100);
            text.setLayoutY(30);
            text.setText(iText);
            arrow.getStyleClass().add("leftArrow");
            arrow.setLayoutY(40);
            arrow.setLayoutX(85);
            MainWindow.setHeadPortrait(head, iHead);
            left.getChildren().add(head);
            left.getChildren().add(text);
            left.getChildren().add(arrow);
            pane.getChildren().add(left);
            return pane;
        }

        public Pane right(String iHead, String iText, double width, double height) {//自己的消息
            pane.setPrefHeight(110 + height);
            head.setLayoutY(10);
            head.setLayoutX(510);
            right.setPrefHeight(height);
            //text=480 0 text = 470 10 460 20 480-width
            text.getStyleClass().add("rightText");
            text.setMinSize(width,height);
            text.setMaxSize(width,height);
            text.setLayoutY(30);
            text.setLayoutX(480 - width);
            text.setText(iText);
            arrow.getStyleClass().add("rightArrow");
            arrow.setLayoutY(40);
            arrow.setLayoutX(475);
            MainWindow.setHeadPortrait(head, iHead);
            right.getChildren().add(head);
            right.getChildren().add(text);
            right.getChildren().add(left);
            right.getChildren().add(arrow);
            right.setLayoutX(150);
            pane.getChildren().add(right);
            return pane;
        }

    }

}
