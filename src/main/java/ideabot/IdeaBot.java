package ideabot;

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuffer;
import java.io.FileOutputStream;
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
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import java.io.File;

/**
 * An App that diplays a random idea
 *
 * @author Kyle Helsing
 */
public class IdeaBot extends Application {

    static final int NOUN_FILE_SIZE = 189;
    static final int ADJ_FILE_SIZE = 998;
    static File adjFile = new File("src/main/java/ideabot/adjectives.txt");
    static File nounFile = new File("src/main/java/ideabot/csnouns.txt");
    String[][] combos;
    int rowIndex;

    public static void initFile(String filename, String num){
	try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            String line = "";
            StringBuffer inputBuffer = new StringBuffer();
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line + " " + num);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();
            file.close();

	    FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
	} catch (Exception e) {
            System.out.println("Problem reading file.");
        } //catch
    } //initFilename
    
    public static String[][] getPairs(){
	//the array to hold the pairs and their indexes to return
	String[][] pairs = new String[20][4];
       
	try{		    
	    for(int loop = 0; loop < 20; loop++){
                //initialize scanners to search the .txt files
		Scanner adjFinder = new Scanner(adjFile);
		Scanner nounFinder = new Scanner(nounFile);

		//randomly generate the adjective and noun lines
		int randAdj = (int)(Math.random() * ADJ_FILE_SIZE);	    
		int randNoun = (int)(Math.random() * NOUN_FILE_SIZE);

		//find the adjective in the .txt file
		for(int i = 0; i < randAdj; i++){
		    adjFinder.nextLine();
		} //for
		String adj = adjFinder.nextLine();
		adj = adj.substring(0, adj.length()-2);
		//find the noun in the .txt file
		for(int i = 0; i < randNoun; i++){
		    nounFinder.nextLine();
		} //for
		String noun = nounFinder.nextLine();
		noun = noun.substring(0, noun.length()-2);
		//assigns the words and their line number to the pairs array
		    for(int j = 0; j < 4; j++){
			switch(j){
			case 0: pairs[loop][j] = adj;
			    break;
			case 1: pairs[loop][j] = noun;
			    break;
			case 2: pairs[loop][j] = String.valueOf(randAdj + 1);
			    break;
			case 3: pairs[loop][j] = String.valueOf(randNoun + 1);
			    break;
			} //switch
		    } //for
		    
		    adjFinder.close();
		nounFinder.close();
	    } //for	    
	}
	catch(Exception e){
	    e.printStackTrace();
	} //catch
	return pairs;
    } //getPairs

    public static int [][]num;
   
    public static int bestCombo(String [][] a,String adjFile,String nounFile){
	num = new int[a.length][2];
	for(int i = 0; i < num.length; i++){
	    num[i][0] = 0;
	    num[i][1] = 0;
	} //for
        try {    
	    for(int i = 0; i < a.length; i++) {
		File adj = new File(adjFile);
		Scanner input = new Scanner(adj); 
		String line = "";
		for(int j = 0; j <= Integer.parseInt(a[i][2]); j++) {
		    line = input.nextLine();
		}//for   
		//System.out.println(line);
		num[i][0] += Integer.parseInt(line.substring(line.length() - 1));
	    }//for
	    for(int i = 0; i < a.length; i++) {
		File nouns = new File(nounFile);
		Scanner input = new Scanner(nouns);
                               
		String line = "";
		for(int j = 0; j <= Integer.parseInt(a[i][3]); j++) {
		    line = input.nextLine();
		}//for   
		//System.out.println(line);
		num[i][1] += Integer.parseInt(line.substring(line.length() - 1));
	    }//for 
	    int max = 0;
	    for(int i = 1; i < num.length; i++) {
		if((num[i][0] + num[i][1]) > (num[max][0] + num[max][1])) {
		    max = i;
		}//if
	    }//for 
	    return max; 
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} //catch
	return 0;
    } //bestCombo

    public static void update(boolean value, String adjFile, String nounFile, String adjective, String noun, int index){
	//adjective
	try {
	    BufferedReader file = new BufferedReader(new FileReader(adjFile));
	    String line = "";
	    StringBuffer inputBuffer = new StringBuffer();
	    while ((line = file.readLine()) != null) {
		inputBuffer.append(line);
		inputBuffer.append('\n');
	    }
	    String inputStr = inputBuffer.toString();
	    file.close();
	    //System.out.println(inputStr); // check that it's inputted right
	    //changes the values in the text file
	    if(value){
		if(num[index][0] < 4)
		    inputStr = inputStr.replace(adjective + " " + Integer.toString(num[index][0]), adjective + " " + Integer.toString(num[index][0] + 1)); 
	    }else{
		if(num[index][0] > 0)
		    inputStr = inputStr.replace(adjective + " " + Integer.toString(num[index][0]), adjective + " " + Integer.toString(num[index][0] - 1));
	    } //if 
	    // check if the new input is right
	    //System.out.println("----------------------------------\n"  + inputStr); 
	    // write the new String with the replaced line OVER the same file
	    FileOutputStream fileOut = new FileOutputStream(adjFile);
	    fileOut.write(inputStr.getBytes());
	    fileOut.close();
	} catch (Exception e) {
	    System.out.println("Problem reading file.");
	} //catch
	//nouns
	try {
	    BufferedReader file = new BufferedReader(new FileReader(nounFile));
	    String line = "";
	    StringBuffer inputBuffer = new StringBuffer();  
	    while ((line = file.readLine()) != null) {
		inputBuffer.append(line);
		inputBuffer.append('\n');
	    }
	    String inputStr = inputBuffer.toString();
	    file.close();
	    //System.out.println(inputStr); // check that it's inputted right
	    if(value){
		if(num[index][1] < 8)
		    inputStr = inputStr.replace(noun + " " + Integer.toString(num[index][1]), noun + " " + Integer.toString(num[index][1] + 1)); 
	    }else{
		if(num[index][1] > 0)
		    inputStr = inputStr.replace(noun + " " + Integer.toString(num[index][1]), noun + " " + Integer.toString(num[index][1] - 1));
	    }
	    // check if the new input is right
	    //System.out.println("----------------------------------\n"  + inputStr);      
	    // write the new String with the replaced line OVER the same file
	    FileOutputStream fileOut = new FileOutputStream(nounFile);
	    fileOut.write(inputStr.getBytes());
	    fileOut.close();
	} catch (Exception e) {
	    System.out.println("Problem reading file.");
	} //catch
    }//update

    //main method
    @Override
    public void start(Stage stage) {
	initUI(stage);
    } // start
    private void initUI(Stage stage){
	combos = getPairs();
	rowIndex = bestCombo(combos, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt");
	//main panel
	BorderPane root = new BorderPane();
	Label idea = new Label(combos[rowIndex][0] + " " + combos[rowIndex][1]);
	idea.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 30; -fx-text-fill: white;");
	VBox center = new VBox();
	center.setAlignment(Pos.CENTER);
	center.setBackground(new Background(new BackgroundFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), .99), CornerRadii.EMPTY, Insets.EMPTY)));
	HBox top = new HBox();
	top.setAlignment(Pos.CENTER);
	HBox bottom = new HBox();
	bottom.setPadding(new Insets(10.0));
	bottom.setAlignment(Pos.CENTER);
	Button good = new Button("I Love It!");
	good.setStyle("-fx-font-size: 2em; -fx-background-color: lightgreen;");
	good.setOnAction(new EventHandler<ActionEvent>(){
		public void handle (ActionEvent t) {
		    center.setBackground(new Background(new BackgroundFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), .99), CornerRadii.EMPTY, Insets.EMPTY)));
		    update(true, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt", combos[rowIndex][0], combos[rowIndex][1], rowIndex);
		    combos = getPairs();
		    rowIndex = bestCombo(combos, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt");
		    idea.setText(combos[rowIndex][0] + " " + combos[rowIndex][1]);
		} // handle
	    });
	Button bad = new Button("That's Dumb!");
	bad.setStyle("-fx-font-size: 2em; -fx-background-color: red;");
	bad.setOnAction(new EventHandler<ActionEvent>(){
		public void handle (ActionEvent t) {
		    center.setBackground(new Background(new BackgroundFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), .99), CornerRadii.EMPTY, Insets.EMPTY)));
		    update(false, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt", combos[rowIndex][0], combos[rowIndex][1], rowIndex);
		    combos = getPairs();
		    rowIndex = bestCombo(combos, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt");
		    idea.setText(combos[rowIndex][0] + " " + combos[rowIndex][1]);
		} // handle
	    });
	Button okay = new Button("Good Words, Bad Combo!");
	okay.setStyle("-fx-font-size: 2em; -fx-background-color: orange;");
	okay.setOnAction(new EventHandler<ActionEvent>(){
		public void handle (ActionEvent t) {
		    center.setBackground(new Background(new BackgroundFill(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), .99), CornerRadii.EMPTY, Insets.EMPTY)));
		    combos = getPairs();
		    rowIndex = bestCombo(combos, "src/main/java/ideabot/adjectives.txt", "src/main/java/ideabot/csnouns.txt");
		    idea.setText(combos[rowIndex][0] + " " + combos[rowIndex][1]);
		} // handle
	    });
	Label title = new Label("Your new \"one in a billion\" idea is:");
	title.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: darkred;");
	top.getChildren().addAll(title);
	root.setTop(top);
	bottom.getChildren().addAll(bad, new Separator(), okay, new Separator(), good);
	root.setBottom(bottom);
	center.getChildren().addAll(idea);
	





	root.setCenter(center);
	root.setMaxWidth(800);
        root.setMaxHeight(460);
        root.setMinWidth(800);
        root.setMinHeight(460);
	//final stuff
	Scene scene = new Scene(root);
	stage.setMaxWidth(800);
	stage.setMaxHeight(485);
	stage.setMinWidth(800);
	stage.setMinHeight(485);
	stage.setTitle("Idea Bot!");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
	
    } //initUI
    

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

