
//Standard javafx imports.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Imports for visual components.
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

//Imports for layout.
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Imports for the Menu
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

//Import for quitting the application.
import javafx.application.Platform;

public class CardsHiLoGUI extends Application {

	// creating an object of DeckOfCards
	DeckOfCards deck = new DeckOfCards();

	/* Declare controls at class scope. */
	// The menubar.
	MenuBar mBar;

	// Menus.
	Menu mnuFile, mnuHelp;

	// MenuItems
	MenuItem fileNewGameItem, fileShuffleItem, fileExitItem, helpAboutItem;

	// Labels to provide status/path messages.
	Label lblFirstCardDealt, lblSecondCardDealt, lblNextCard, lblGameStatus;

	// The image fields.
	Image imgFirstCard, imgSecondCard;

	// Where to show the image. The image view fields.
	ImageView imgFirstCardView, imgSecondCardView;

	// Buttons fields.
	Button btnDealFirstCard, btnDealSecondCard;

	// Radio button fields.
	RadioButton rbHigher, rbLower;

	// Used such that only a single Toggle within the ToggleGroup may be selected at
	// any one time.
	ToggleGroup tgChoice;

	// VBox
	VBox vbLeft, vbRight, vbCenter;

	// Progress controls.
	ProgressBar progBar;
	ProgressIndicator progIndicator;

	// HBox for progress bar and indicator.
	HBox hbProg, hbBottom;

	// Strings for storing the image names/paths.
	String cLeft = null;
	String cRight = null;

	// For storing a card after dealing top
	Card cardLeft;
	Card cardRight;

	public void init() throws Exception {

		// Instantiate components.
		mBar = new MenuBar();

		// Instantianting the Menu for File.
		mnuFile = new Menu("File");

		// Creating a menu item for the file menu.
		fileNewGameItem = new MenuItem("New Game");
		mnuFile.getItems().add(fileNewGameItem);
		fileNewGameItem.setOnAction(ae -> {
			deck.deckOfCards();
			deck.shuffle();

			// Card images at the start of a new game loaded from computer.
			cLeft = "file:cards/card_back_blue.png";
			cRight = "file:cards/card_back_red.png";

			// The progress bar and indicator set to start at position zero.
			progBar.setProgress(0);
			progIndicator.setProgress(0);

			// Creating image variable.
			imgFirstCard = new Image(cLeft);
			imgSecondCard = new Image(cRight);

			// Where to show the image. The image view.
			imgFirstCardView.setImage(imgFirstCard);
			imgSecondCardView.setImage(imgSecondCard);

			btnDealSecondCard.setDisable(true);
			btnDealFirstCard.setDisable(false);

			lblGameStatus.setText("NEW GAME");
			// CSS for the label.
			lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
			lblGameStatus.setTextFill(Color.BLACK);

		});

		// Creating another MenuItem for the File menu.
		fileShuffleItem = new MenuItem("Shuffle");
		mnuFile.getItems().add(fileShuffleItem);
		fileShuffleItem.setOnAction(ae -> {
			deck.shuffle();
		});

		// Creating another MenuItem for the File menu.
		fileExitItem = new MenuItem("Exit");
		mnuFile.getItems().add(fileExitItem);
		fileExitItem.setOnAction(ae -> Platform.exit());

		// Instantiating the second menu for Help.
		mnuHelp = new Menu("Help");

		// Create menu items for the Help menu.
		helpAboutItem = new MenuItem("About");
		mnuHelp.getItems().add(helpAboutItem);
		helpAboutItem.setOnAction(ae -> showAboutDialog());

		// Add menus to the menubar.
		mBar.getMenus().add(mnuFile);
		mBar.getMenus().add(mnuHelp);

		// Labels
		lblFirstCardDealt = new Label("First Card Dealt:");
		// CSS for the label.
//		lblFirstCardDealt.setFont(Font.font("Calibri",FontWeight.BOLD,FontPosture.REGULAR,12));  
//		lblFirstCardDealt.setTextFill(Color.BLACK);

		lblSecondCardDealt = new Label("Second Card Dealt:");
		// CSS for the label.
//		lblSecondCardDealt.setFont(Font.font("Calibri",FontWeight.BOLD,FontPosture.REGULAR,12));  
//		lblSecondCardDealt.setTextFill(Color.BLACK);

		lblNextCard = new Label("Next Card will be:");
		// CSS for label
		lblNextCard.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
		lblNextCard.setTextFill(Color.BLACK);

		lblGameStatus = new Label(" ");
		// CSS for Label
		lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
		lblGameStatus.setTextFill(Color.BLACK);

		// String for storing file/image path
		cLeft = "file:cards/card_back_blue.png";
		cRight = "file:cards/card_back_red.png";

		// action event on first button.
		btnDealFirstCard = new Button("<-Deal First Card");
		btnDealFirstCard.setOnAction(ae -> {

			cardLeft = deck.dealTopCard();

			if (deck.isEmpty()) {

				lblGameStatus.setText("DECK IS EMPTY");
				// CSS for Label
				lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
				lblGameStatus.setTextFill(Color.BLACK);

				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(true);

			} else {

				imgFirstCard = new Image(cardLeft.toString());

				imgFirstCardView.setImage(imgFirstCard);

				btnDealSecondCard.setDisable(false);
				btnDealFirstCard.setDisable(true);
			}
		});

		// action event on second button
		btnDealSecondCard = new Button("Deal Second Card->");

		btnDealSecondCard.setOnAction(ae -> {

			cardRight = deck.dealTopCard();

			if (deck.isEmpty()) {

				lblGameStatus.setText("DECK IS EMPTY");
				// CSS for label.
				lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
				lblGameStatus.setTextFill(Color.BLACK);

				// If deck is empty, disable the buttons.
				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(true);
			} else {

				imgSecondCard = new Image(cardRight.toString());
				imgSecondCardView.setImage(imgSecondCard);

				btnDealSecondCard.setDisable(true);
				btnDealFirstCard.setDisable(false);

				if (rbHigher.isSelected() && cardRight.rankIsGreaterThan(cardLeft)) {

					lblGameStatus.setText("Higher, You Win");
					lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
					lblGameStatus.setTextFill(Color.BLACK);

					// Get the current progress
					double progValue = progBar.getProgress();

					// Increase it by 0.2
					progValue = progValue + 0.2;

					if (progValue > 0.90) {
						progValue = 1.0;
					}

					// If the progress is >= 0.7 color the progress bar red.
					if (progValue >= 0.7) {
						// Set a style. Show a red progress bar.
						progBar.setStyle("-fx-accent: red;");
					} // if
					else {
						progBar.setStyle("-fx-accent: green;");
						progIndicator.setStyle("-fx-accent: green;");
					} // else

					// To indicate that the game is over.
					if (progValue == 1.0) {
						lblGameStatus.setText("GAME OVER.");
						lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
						lblGameStatus.setTextFill(Color.BLACK);

						// If there is 5 consecutive wins, disable the buttons to indicate that the game
						// is over.
						btnDealSecondCard.setDisable(true);
						btnDealFirstCard.setDisable(true);
					}

					// Set the new increased progress.
					progBar.setProgress(progValue);

					// Also set the progress of the progress indicator.
					progIndicator.setProgress(progValue);
				}

				else if ((rbHigher.isSelected()) && cardRight.rankIsLessThan(cardLeft)) {
					lblGameStatus.setText("Lower, You Lose");

					// Get the current progress.
					double progValue = progBar.getProgress();

					// Set to zero.
					progValue = 0;

					// Stop the progress controls going off-scale.
					// If value is less than 0.1, then make it 0.
					if (progValue < 0.1) {
						progValue = 0;
					} // if

					// If the progress is >= 0.7 color the progress bar red.
					if (progValue >= 0.7) {
						// Set a style. Show a red progress bar.
						progBar.setStyle("-fx-accent: red;");
						progIndicator.setStyle("-fx-accent: red;");
					} // if
					else {
						progBar.setStyle("-fx-accent: green;");
						progIndicator.setStyle("-fx-accent: green;");
					} // else

					// Set the new increased progress.
					progBar.setProgress(progValue);

					// Also set the progress of the progress indicator.
					progIndicator.setProgress(progValue);
				}

				else if ((rbLower.isSelected()) && cardRight.rankIsGreaterThan(cardLeft)) {

					lblGameStatus.setText("Higher, You Lose");
					lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
					lblGameStatus.setTextFill(Color.BLACK);

					// Get the current progress.
					double progValue = progBar.getProgress();

					// Set to zero.
					progValue = 0;

					// Stop the progress controls going off-scale.
					// If value is less than 0.1, then make it 0.
					if (progValue < 0.1) {
						progValue = 0;
					} // if

					// If the progress is >= 0.7 color the progress bar red.
					if (progValue >= 0.7) {
						// Set a style. Show a red progress bar.
						progBar.setStyle("-fx-accent: red;");
						progIndicator.setStyle("-fx-accent: red;");
					} // if
					else {
						progBar.setStyle("-fx-accent: green;");
						progIndicator.setStyle("-fx-accent: green;");
					} // else

					// Set the new increased progress.
					progBar.setProgress(progValue);

					// Also set the progress of the progress indicator.
					progIndicator.setProgress(progValue);
				}

				else if ((rbLower.isSelected()) && cardRight.rankIsLessThan(cardLeft)) {
					lblGameStatus.setText("Lower, You Win");
					lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
					lblGameStatus.setTextFill(Color.BLACK);


					// Get the current progress
					double progValue = progBar.getProgress();

					// Increase it by 0.2
					progValue = progValue + 0.2;

					if (progValue > 0.90) {
						progValue = 1.0;
					}

					// If the progress is >= 0.7 color the progress bar red.
					if (progValue >= 0.7) {
						// Set a style. Show a red progress bar.
						progBar.setStyle("-fx-accent: red;");
					} // if
					else {
						progBar.setStyle("-fx-accent: green;");
						progIndicator.setStyle("-fx-accent: green;");
					} // else

					// To indicate that the game is over.
					if (progValue == 1.0) {
						lblGameStatus.setText("GAME OVER.");
						lblGameStatus.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
						lblGameStatus.setTextFill(Color.BLACK);

						btnDealSecondCard.setDisable(true);
						btnDealFirstCard.setDisable(true);
					}

					// Set the new increased progress.
					progBar.setProgress(progValue);

					// Also set the progress of the progress indicator.
					progIndicator.setProgress(progValue);
				}

				else if ((rbLower.isSelected()) || (rbHigher.isSelected()) && cardRight.rankIsEqualTo(cardLeft)) {
					lblGameStatus.setText("Equal. You lose.");

					// Get the current progress.
					double progValue = progBar.getProgress();

					// Set to zero.
					progValue = 0;

					// Stop the progress controls going off-scale.
					// If value is less than 0.1, then make it 0.
					if (progValue < 0.1) {
						progValue = 0;
					} // if

					// If the progress is >= 0.7 color the progress bar red.
					if (progValue >= 0.7) {
						// Set a style. Show a red progress bar.
						progBar.setStyle("-fx-accent: red;");
						progIndicator.setStyle("-fx-accent: red;");
					} // if
					else {
						progBar.setStyle("-fx-accent: green;");
						progIndicator.setStyle("-fx-accent: green;");
					} // else

					// Set the new increased progress.
					progBar.setProgress(progValue);

					// Also set the progress of the progress indicator.
					progIndicator.setProgress(progValue);
				}
			}
		});

		// Radio buttons.
		rbHigher = new RadioButton("Higher");
		rbLower = new RadioButton("Lower");

		// a toggle group for the radio buttons
		tgChoice = new ToggleGroup();

		// set the toggle group for the radio buttons
		rbHigher.setToggleGroup(tgChoice);
		rbLower.setToggleGroup(tgChoice);

	}

	@Override
	public void start(Stage pStage) throws Exception {
		deck.deckOfCards();
		deck.shuffle();

		btnDealSecondCard.setDisable(true);
		btnDealFirstCard.setDisable(false);

		btnDealFirstCard.setPrefWidth(130);
		btnDealSecondCard.setPrefWidth(130);

		// The image.
		imgFirstCard = new Image(cLeft);
		imgSecondCard = new Image(cRight);

		// Where to show the image. The image view.
		imgFirstCardView = new ImageView(imgFirstCard);
		imgSecondCardView = new ImageView(imgSecondCard);
		// Set the width and height.
		pStage.setWidth(520);
		pStage.setHeight(380);

		// Create a layout.
		BorderPane bp = new BorderPane();
		// Creating VBoxes
		vbLeft = new VBox(5);
		vbLeft.getChildren().add(lblFirstCardDealt);
		vbLeft.getChildren().add(imgFirstCardView);
		vbLeft.setPadding(new Insets(20));

		vbRight = new VBox(5);
		vbRight.getChildren().addAll(lblSecondCardDealt, imgSecondCardView);
		vbRight.setPadding(new Insets(20));

		vbCenter = new VBox(5);
		vbCenter.getChildren().add(lblNextCard);
		vbCenter.getChildren().add(rbHigher);
		vbCenter.getChildren().add(rbLower);
		vbCenter.getChildren().add(btnDealFirstCard);
		vbCenter.getChildren().add(btnDealSecondCard);
		vbCenter.setPadding(new Insets(40, 25, 10, 30));
		vbCenter.setSpacing(5);


		progBar = new ProgressBar(0);
		progIndicator = new ProgressIndicator(0);
		lblGameStatus = new Label("");
		// lblGameStatus.setAlignment(Pos.BASELINE_RIGHT);

		// Manage component sizes.
		progBar.setMinWidth(150);

		hbProg = new HBox();
		hbProg.getChildren().addAll(progBar, progIndicator);
		hbProg.setAlignment(Pos.CENTER);

		hbBottom = new HBox();
		hbBottom.getChildren().addAll(hbProg, lblGameStatus);
		hbBottom.setTranslateX(150);
		hbBottom.setTranslateY(-20);
		hbBottom.setSpacing(25);

		// Add components to the layout.
		bp.setCenter(vbCenter);
		bp.setTop(mBar);
		bp.setBottom(hbBottom);
		bp.setLeft(vbLeft);
		bp.setRight(vbRight);

		// Set the title.
		pStage.setTitle("Hi-Lo Card Game");
		pStage.setResizable(false);

		// Create a scene.
		Scene s = new Scene(bp);

		// Add a style
		s.getStylesheets().add("progStyle.css");

		// Set the scene.
		pStage.setScene(s);

		// Show the stage.
		pStage.show();

	}

	private void showAboutDialog() {

		// Show an information alert with application name.
		// and version information.
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About App Designer.");
		alert.setHeaderText("CardsHiLoGUI");
		alert.setContentText("CardsHiLoGUI" + "\nDesigned by student: OJO PETER." + "\nStudent Number: 2974470"
				+ "\nCopyright HDC-FT 2019.");

		// Show the alert(dialog) and wait for user response.
		alert.showAndWait();

	}// showAboutDialog()

	public static void main(String[] args) {
		// Launch the application.
		launch();
	}

}
