import model.Battlefield;

/*public class Main {
    public static void main(String[] args){
        Battlefield b1 = new Battlefield("battlefields/battlefield1.json");
        int[][] battlefield = b1.getBattlefield();
        for (int j = 0; j < battlefield[0].length; j++) {
            for (int i = 0; i < battlefield.length; i++) {
                System.out.print(battlefield[i][j]+"   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TilePane root = FXMLLoader.load(getClass().getResource("view/view.fxml"));
			Scene scene = new Scene(root,240,432);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
