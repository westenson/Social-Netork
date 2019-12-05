package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/***************************************************************************************************
*
* @author 		Dan Gerstl, Cecelia Peterson
*
* @version	 	1.0 <p>
*
* File:			Main.java<p>
*
* Date:			December 2, 2019<p>
*
* Purpose:		aTeam p2 - GUI
*
* Description:  Creates a GUI as a mockup for our aTeam design and fills it with hardcoded data
* 				to provide an example for what it will look like when the data structure is 
* 				implemented.
*
* Comment:		
*
***************************************************************************************************/

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/*** Local Variables ***/
			
			BorderPane root  = new BorderPane();
			
			VBox centerBox   = new VBox();
			
			HBox topPanel    = new HBox();  
			HBox bottomPanel = new HBox(); 			
			
			/*** Add components to centerBox ***/
			
			centerBox.getChildren().add(createCanvasPane());
			centerBox.getChildren().add(createButtonPane());
			
			/*** Create top and bottom panels ***/
			
			topPanel    = createTopPanel();  
			bottomPanel = createBottomPanel();  
			
			/*** Add components to root ***/
			
			root.setTop(topPanel);		 
			root.setCenter(centerBox);	
			root.setBottom(bottomPanel);     
			
			/*** Set scene ***/
			
			Scene scene = new Scene(root,725,550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			/*** Set Stage and show ***/
			
			primaryStage.setTitle("Network Visualizer");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*** Panel creation methods ***/
	
	private HBox createCanvasPane() {
		
		/*** Local Constants ***/
		
		final double CANVAS_X_SIZE = 300;
		final double CANVAS_Y_SIZE = 300;
		
		/*** Local Variables ***/
		
		HBox mainBox = new HBox();		
		
		Canvas canvas = new Canvas(CANVAS_X_SIZE, CANVAS_Y_SIZE);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Label lblRadioChoice = new Label("All friends (default)");
				
		VBox rightBox = new VBox();
		
		ListView<String> lvFriends = new ListView<String>();
		
		/*** HARDCODED DATA FOR EXAMPLE ***/
		
		ObservableList<String> friendsList = FXCollections.observableArrayList ("Friend 1", 
											 "Friend 2", "Friend 3", "Friend 4");
		
		lvFriends.setItems(friendsList);
		
		/*** Add components to VBox ***/
		
		rightBox.getChildren().add(lblRadioChoice);
		rightBox.getChildren().add(lvFriends);
		
		/*** Set padding ***/
		
		mainBox.setPadding(new Insets(15, 15, 15, 15));
		mainBox.setSpacing(130);
		
		/*** Add EXAMPLE FRIENDS ***/
		
		//drawExampleFriends(gc, CANVAS_X_SIZE, CANVAS_Y_SIZE);
		
		drawFriends(gc, CANVAS_X_SIZE, CANVAS_Y_SIZE, "USER");
		
		/*** Add components to main pane ***/
		
		mainBox.getChildren().add(canvas);
		mainBox.getChildren().add(rightBox);
				
		return mainBox;
	}
		
	private HBox createButtonPane() {
		
		/*** Local Constants ***/
		
		final double BUTTON_HEIGHT  = 20.0;
		final double BUTTON_WIDTH   = 90.0;
		final double BUTTON_SPACING = 10.0;
		
		/*** Local Variables ***/

		HBox buttonPane = new HBox();
		
		ArrayList<Button> buttonList = new ArrayList<Button>();
		
		Button btnClear   = new Button("Clear"   );
		Button btnNewUser = new Button("New User");
		Button btnUndo    = new Button("Undo"    );
		Button btnRedo    = new Button("Redo"    );
		Button btnLoad    = new Button("Load"    );
		Button btnExport  = new Button("Export"  );
		Button btnExit    = new Button("Exit"    );
		
		/*** Add buttons to Array ***/
		
		buttonList.add(btnClear  );
		buttonList.add(btnNewUser);
		buttonList.add(btnUndo   );
		buttonList.add(btnRedo   );
		buttonList.add(btnLoad   );
		buttonList.add(btnExport );
		buttonList.add(btnExit   );	
		
		/*** Set spacing for buttons ***/
		
		buttonPane.setPadding(new Insets(0, 15, 0, 15));
		buttonPane.setSpacing(BUTTON_SPACING);
		
		/*** Set button size and add to pane***/
		
		for (Button b : buttonList) {
			
			b.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			
			buttonPane.getChildren().add(b);
		}
		
		return buttonPane;
	}
	
	private HBox createTopPanel() { 
		
		HBox topPanel = new HBox();
		
		// create radio buttons for left-most feature
    	RadioButton rb1 = new RadioButton("Show all friends");
    	RadioButton rb2 = new RadioButton("Show mutual friends");
    	RadioButton rb3 = new RadioButton("Show shortest path");

        VBox tGroupContainer = new VBox(rb1, rb2, rb3);
    	final ToggleGroup tGroup = new ToggleGroup();
    	
    	rb1.setSelected(true);
    	rb1.setToggleGroup(tGroup);
    	rb2.setToggleGroup(tGroup);
    	rb3.setToggleGroup(tGroup);
    	
    	// create combo box for main profile
    	VBox v1 = new VBox();
    	Label l1 = new Label("Main Profile");
    	ObservableList<String> users = 
    			FXCollections.observableArrayList("User 1","User 2","User 3");
    	ComboBox c1 = new ComboBox(users);
    	v1.getChildren().addAll(l1, c1);
    	
    	// create combo box for friend
    	VBox v2 = new VBox();
    	Label l2 = new Label("Friend");
    	ObservableList<String> friends = 
    			FXCollections.observableArrayList("Friend 1","Friend 2","Friend 3");
    	ComboBox c2 = new ComboBox(users);
    	v2.getChildren().addAll(l2, c2);
    	
    	// create button for remove user and remove friendship
    	VBox v3 = new VBox();
		Button btnRmUser = new Button("Remove User");
		v3.getChildren().add(btnRmUser);
		v3.snappedBottomInset();
    	VBox v4 = new VBox();
    	Button btnRmFriend = new Button("Remove Friendship");
		v4.getChildren().add(btnRmFriend);
		v4.snappedBottomInset();
				
		btnRmUser.setPrefSize(150.0, 40.0);
		btnRmFriend.setPrefSize(150.0, 40.0);
    	
    	// add all nodes to top panel
    	topPanel.getChildren().addAll(tGroupContainer, v1, v2, btnRmUser, btnRmFriend);
		topPanel.setSpacing(20);
		
		topPanel.setPadding(new Insets(15, 15, 0, 15));

		return topPanel;
		
	}
	
	private HBox createBottomPanel() { 
		
		HBox bottomPanel = new HBox();
			
		// create variable for previous action
		String prevAction = "Last Action Taken";
		Label lastAction = new Label(prevAction);
		
		// create variable for number of current users
		int currentUsers = 4;
		Label userCount = new Label("Total Current Users: " + currentUsers);
		
		bottomPanel.getChildren().addAll(lastAction, userCount);
		bottomPanel.setSpacing(440);
		
		bottomPanel.setPadding(new Insets(15, 15, 15, 15));
		
		return bottomPanel;
		
	}
	
	/*** Canvas friend drawing method ***/
	
	private void drawExampleFriends(GraphicsContext gc, double x, double y) {
		
		/*** Local Constants ***/
		
		final double CIRCLE_HEIGHT    = 50.0;
		final double CIRCLE_WIDTH     = 50.0;
		final double CIRCLE_MID		  = CIRCLE_HEIGHT / 2;
		final double START_X 	      = 0.0;
		final double START_Y	      = 0.0;
		final double FRIEND_X_OFFSET  = 75.0;
		final double FRIEND_Y_OFFSET  = 75.0;
		final double CENTERING_OFFSET = 4.0;
		
		/*** Local Variables ***/
		
		double centerX = x / 2.0;
		double centerY = y / 2.0;
		
		/*** Set drawing properties ***/
		
		gc.setFill(Color.CRIMSON);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		
		/*** Move to center of canvas ***/
		
		gc.translate(centerX, centerY);
		
		/*** Draw main user ***/
		
		gc.fillOval(START_X, START_Y, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		
		/*** Label main user ***/
		
		gc.strokeText("Current", CENTERING_OFFSET, CIRCLE_MID + CENTERING_OFFSET);
		
		/*** Set color for friends ***/
		
		gc.setFill(Color.INDIANRED);
		
		/*** Draw vertical friends ***/
		
		gc.fillOval(START_X, START_Y - FRIEND_Y_OFFSET, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		gc.fillOval(START_X, START_Y + FRIEND_Y_OFFSET, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		
		/*** Label vertical friends ***/
		
		gc.strokeText("Friend 1", CENTERING_OFFSET, CIRCLE_MID - FRIEND_Y_OFFSET + CENTERING_OFFSET);
		gc.strokeText("Friend 3", CENTERING_OFFSET, CIRCLE_MID + FRIEND_Y_OFFSET + CENTERING_OFFSET);
		
		/*** Draw lines to vertical friends ***/
		
		gc.strokeLine(CIRCLE_MID, 0, CIRCLE_MID, CIRCLE_HEIGHT - FRIEND_Y_OFFSET);
		gc.strokeLine(CIRCLE_MID, CIRCLE_HEIGHT, CIRCLE_MID, FRIEND_Y_OFFSET);
		
		/*** Draw horizontal friends ***/
		
		gc.fillOval(START_X - FRIEND_X_OFFSET, START_Y, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		gc.fillOval(START_X + FRIEND_X_OFFSET, START_Y, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		
		/*** Label horizontal friends ***/
		
		gc.strokeText("Friend 2",-FRIEND_X_OFFSET + CENTERING_OFFSET, CIRCLE_MID + CENTERING_OFFSET);
		gc.strokeText("Friend 4", FRIEND_X_OFFSET + CENTERING_OFFSET, CIRCLE_MID + CENTERING_OFFSET);
		
		/*** Draw lines to horizontal friends ***/
		
		gc.strokeLine(0, CIRCLE_HEIGHT / 2, CIRCLE_WIDTH - FRIEND_X_OFFSET, CIRCLE_HEIGHT / 2);
		gc.strokeLine(CIRCLE_WIDTH, CIRCLE_HEIGHT / 2, FRIEND_X_OFFSET, CIRCLE_HEIGHT / 2);		
	}
	
	private void drawFriends(GraphicsContext gc, double x, double y, String user) {
		
		/*** Local Constants ***/
		
		final double CIRCLE_HEIGHT    = 50.0;
		final double CIRCLE_WIDTH     = 50.0;
		final double CIRCLE_MID		  = CIRCLE_HEIGHT / 2;
		final double START_X 	      = 0.0;
		final double START_Y	      = 0.0;
		final double FRIEND_X_OFFSET  = 75.0;
		final double FRIEND_Y_OFFSET  = 75.0;
		final double CENTERING_OFFSET = 10.0;
		
		/*** Local Variables ***/
		
		double centerX = x / 2.0;
		double centerY = y / 2.0;
		
		/*** Set drawing properties ***/
		
		gc.setFill(Color.CRIMSON);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		
		/*** Move to center of canvas ***/
		
		gc.translate(centerX, centerY);
		
		/*** Draw main user ***/
		
		gc.fillOval(START_X, START_Y, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		
		/*** Label main user ***/
		
		gc.strokeText(user, CENTERING_OFFSET, CIRCLE_MID + CENTERING_OFFSET / 2);
		
		/*** Set color for friends ***/
		
		gc.setFill(Color.INDIANRED);
		
		gc.fillOval(START_X, START_Y - FRIEND_Y_OFFSET, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		gc.strokeText("Friend 1", CENTERING_OFFSET, CIRCLE_MID - FRIEND_Y_OFFSET + CENTERING_OFFSET);
		gc.strokeLine(CIRCLE_MID, 0, CIRCLE_MID, CIRCLE_HEIGHT - FRIEND_Y_OFFSET);
		
		gc.rotate(90.0);
		
		gc.fillOval(START_X, START_Y - FRIEND_Y_OFFSET, CIRCLE_WIDTH, CIRCLE_HEIGHT);
		gc.strokeLine(CIRCLE_MID, 0, CIRCLE_MID, CIRCLE_HEIGHT - FRIEND_Y_OFFSET);
		
		gc.rotate(-90.0);
		gc.translate(FRIEND_X_OFFSET, FRIEND_Y_OFFSET);
		
		
		gc.strokeText("Friend 2", CENTERING_OFFSET, CIRCLE_MID - FRIEND_Y_OFFSET + CENTERING_OFFSET);
		
	}
	
	/*** Application ***/
	
	public static void main(String[] args) {
		launch(args);
	}
}
