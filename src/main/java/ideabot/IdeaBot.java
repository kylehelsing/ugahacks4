package ideabot;

//imports
import java.util.*;
import javafx.scene.control.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Separator;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.stage.Modality;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;
import java.io.BufferedReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
/**
 * An App that diplays a random idea
 *
 * @author Kyle Helsing
 */
public class IdeaBot extends Application {
  //main method
    @Override
    public void start(Stage stage) {
          stage.sizeToScene();
          stage.show();
    } // handle
    public static void main(String[] args) {
      try {
        Application.launch(args);

      } catch (UnsupportedOperationException e) {
        System.out.println(e);
        System.err.println("If this is a DISPLAY problem, then your X server connection");
        System.err.println("has likely timed out. This can generally be fixed by logging");
        System.err.println("out and logging back in.");
        System.exit(1);
      } // try
    } // main

} // IdeaBot

