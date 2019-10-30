package test;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.Cypher;

/**
 * Gossip_Client
 * test
 *
 * @author GOLD
 * @date 2019/10/27
 */
public class CypherTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("中文测试");
        String message=Cypher.encode("中文行不行呢？我猜可以，你觉得呢？\n"+
                "那肯定行呀，我写的代码怎么可能有错？\n");
        Cypher.decode(message);
        System.out.println("\nEnglish Test");
        String message1=Cypher.encode("How to do?if the test too long.");
        Cypher.decode(message1);
        System.exit(0);
    }

}
