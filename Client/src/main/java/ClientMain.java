import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.tools.jaotc.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tom.controller.LoginController;
import tom.controller.MainController;
import tom.services.ITomService;

import java.io.IOException;

public class ClientMain extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("In start");

        ApplicationContext factory = new
                ClassPathXmlApplicationContext("classpath:spring-client.xml");
        ITomService server = (ITomService)factory.getBean("tom");
        initialize(stage,server);

    }

    public void initialize(Stage primaryStage,ITomService server) throws IOException {

        FXMLLoader loginLoader=new FXMLLoader(getClass().getResource("/views/loginWindow.fxml"));
        Parent loginP=loginLoader.load();

        FXMLLoader mainLoader=new FXMLLoader(getClass().getResource("/views/mainWindow.fxml"));



        Parent mainP=mainLoader.load();

        LoginController loginController = loginLoader.getController();
        MainController mainController = mainLoader.getController();

        loginController.setServices(server,mainP,mainController,primaryStage);

        primaryStage.setScene(new Scene(loginP));
        primaryStage.show();

    }
}
