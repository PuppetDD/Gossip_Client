package test;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.Cypher;
import utils.Tool;

/**
 * Gossip_Client
 * test
 *
 * @author GOLD
 * @date 2019/10/28
 */
public class ToolTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Tool Test");
        String message= Cypher.encode("中文行不行呢？我猜可以，你觉得呢？\n"+
                "那肯定行呀，我写的代码怎么可能有错？\n");
        String target=Cypher.decode(message);
        double width= Tool.getWidth(target);
        double height=Tool.getHeight(target);
        System.out.println("Width:"+width);
        System.out.println("Height:"+height);
        System.exit(0);
    }

}
