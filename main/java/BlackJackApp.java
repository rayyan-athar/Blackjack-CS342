// RAYYAN ATHAR
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class BlackJackApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	public ImageView makeImage(String path) {
		// Takes in card image path and creates an image view returning it
		ImageView i = new ImageView(new Image(path));
		i.setFitHeight(131);
		i.setFitWidth(90);
		return i;
	}

	public static boolean isDouble(String str) {
		// Function takes in string and returns true if it is a valid double and false if it is not
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// Function takes in card and returns its corresponding image path
	public String getImagePath(Card C) {return C.getValue() + C.getSuit() + ".png";}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("BLACKJACK");

		BlackjackGame game = new BlackjackGame();
		AtomicInteger playerIndex = new AtomicInteger();
		AtomicInteger bankerIndex = new AtomicInteger();

		// **************************** START SCENE**********************************

		// Start scene includes title text and three buttons in a Hbox for their various purposes
		Text tStart = new Text("Blackjack️");
		tStart.setStyle("-fx-font-family: Monospaced; -fx-font-size: 60px; -fx-fill: white;");

		Button bStart = new Button("PLAY");
		bStart.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		Button bRules = new Button("HOW TO PLAY");
		bRules.setStyle("-fx-font-size: 18px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-pref-width: 180px; -fx-pref-height: 40px;");

		Button bCloseStart = new Button("CLOSE");
		bCloseStart.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		HBox hStart = new HBox(20, bStart, bRules, bCloseStart);
		hStart.setAlignment(Pos.CENTER);

		VBox vStart = new VBox(20, tStart, hStart);
		vStart.setAlignment(Pos.CENTER);

		BorderPane bpStart = new BorderPane();
		bpStart.setCenter(vStart);
		bpStart.setStyle("-fx-padding: 100px; -fx-background-color: #1E5C3A;");

		Scene sceneStart = new Scene(bpStart, 700, 700);

		// **************************** RULES SCENE**********************************

		// Rules Scene displays all the rules of the game
		Button bCloseRules = new Button("CLOSE");
		bCloseRules.setStyle("-fx-background-color: #e53935; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
		bCloseRules.setMinWidth(100);
		bCloseRules.setMinHeight(50);

		Button bBack = new Button("GO BACK");
		bBack.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
		bBack.setMinWidth(100);
		bBack.setMinHeight(50);

		HBox hButtons = new HBox(20, bBack, bCloseRules);
		hButtons.setPadding(new Insets(25));
		hButtons.setAlignment(Pos.CENTER);

		// Text for rules
		Text rules = new Text("Rules");
		rules.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 36px; -fx-font-weight: bold; -fx-font-style: italic; -fx-fill: white");

		Text[] ruleTexts = {
				new Text("1. At the start of the game, two cards are dealt to each player. When given two cards, their values are added."),
				new Text("2. Face cards are worth 10 and Aces are worth either 1 or 11, depending on what is most helpful to the player."),
				new Text("3. Once seeing the two cards, the user has an option to stay on their value or to ”hit” and receive another card."),
				new Text("4. They may continue to ”hit” until the user decides to stay or their numerical value becomes 22 or greater. This is called a ”bust” and the user loses the hand regardless of the dealer’s action."),
				new Text("5. Once the user has decided to stay, it becomes the dealer’s turn. The dealer must ”hit” on all hands 16 and lower and must stay on all hands 17 and higher."),
				new Text("6. At the end, whoever’s hand is higher wins. If both players have the same value hand, it is a draw and the hand is over without any money changing hands."),
				new Text("7. Finally, if the player hits 21 on two cards by having an Ace and a card with value 10, then that player has ”blackjack” and wins 150% of their winnings unless the dealer also has also hit a ”blackjack”, in which case the game is a draw.")
		};

		// Set style for each rule for it to wrap around
		for (Text ruleText : ruleTexts) {
			ruleText.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 17; -fx-fill: white");
			ruleText.setWrappingWidth(675);
		}


		VBox vRules = new VBox(10, rules);
		vRules.getChildren().addAll(ruleTexts);
		vRules.setPadding(new Insets(20));
		vRules.setAlignment(Pos.CENTER);


		BorderPane bpRules = new BorderPane();
		bpRules.setCenter(vRules);
		bpRules.setBottom(hButtons);
		bpRules.setStyle("-fx-background-color: #1E5C3A;");

		Scene sceneRules = new Scene(bpRules, 700, 700);

		// ************************ MONEY SCENE ************************************
		Text errorLabelMoney = new Text();
		errorLabelMoney.setStyle("-fx-fill: #d93d4c; -fx-font-size: 18px;");
		errorLabelMoney.setTextAlignment(TextAlignment.CENTER);

		Text promptMoney = new Text("Enter your starting amount");
		promptMoney.setStyle("-fx-fill: white; -fx-font-size: 30px");

		Button bCloseMoney = new Button("CLOSE");
		bCloseMoney.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		TextField tMoney = new TextField();
		tMoney.setStyle("-fx-font-size: 18px; -fx-control-inner-background: lightyellow; -fx-pref-width: 280px;");

		Button bMoney = new Button("Set Money");
		bMoney.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		HBox hMoneyButtons = new HBox(20, bMoney, bCloseMoney);
		hMoneyButtons.setAlignment(Pos.CENTER);

		VBox vMoneyContent = new VBox(10, promptMoney,errorLabelMoney, tMoney);
		vMoneyContent.setAlignment(Pos.CENTER);

		VBox vMoney = new VBox(20, vMoneyContent, hMoneyButtons);
		vMoney.setAlignment(Pos.CENTER);

		BorderPane bpMoney = new BorderPane();
		bpMoney.setCenter(vMoney);
		bpMoney.setStyle("-fx-padding: 100px; -fx-background-color: #1E5C3A;");

		Scene sceneMoney = new Scene(bpMoney, 700, 700);

		// ************************ BET SCENE ************************************
		Text errorLabelBet = new Text();
		errorLabelBet.setStyle("-fx-fill: #d93d4c; -fx-font-size: 18px;");
		errorLabelBet.setTextAlignment(TextAlignment.CENTER);

		Text promptBet = new Text("Enter your starting bet");
		promptBet.setStyle("-fx-fill: white; -fx-font-size: 30px");

		Button bCloseBet = new Button("CLOSE");
		bCloseBet.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		TextField tBet = new TextField();
		tBet.setStyle("-fx-font-size: 18px; -fx-control-inner-background: lightyellow; -fx-pref-width: 280px;");

		Button bBet = new Button("Set Bet");
		bBet.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		HBox hBetButtons = new HBox(20, bBet, bCloseBet);
		hBetButtons.setAlignment(Pos.CENTER);

		VBox vBetContent = new VBox(10, promptBet, errorLabelBet, tBet);
		vBetContent.setAlignment(Pos.CENTER);

		VBox vBet = new VBox(20, vBetContent, hBetButtons);
		vBet.setAlignment(Pos.CENTER);

		BorderPane bpBet = new BorderPane();
		bpBet.setCenter(vBet);
		bpBet.setStyle("-fx-padding: 100px; -fx-background-color: #1E5C3A;");

		Scene sceneBet = new Scene(bpBet, 700, 700);


		// ************************ GAME SCENE ************************************


		Button hit = new Button("HIT");
		hit.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");
		Button stand = new Button("STAND");
		stand.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");
		Button replay = new Button("REPLAY");
		replay.setStyle("-fx-font-size: 18px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");
		Button exitGame = new Button("EXIT");
		exitGame.setStyle("-fx-font-size: 18px; -fx-background-color: #607D8B; -fx-text-fill: white; -fx-pref-width: 120px; -fx-pref-height: 40px;");

		HBox hGameMoney = new HBox(20);
		hGameMoney.setAlignment(Pos.CENTER);

		HBox hPlayerValue = new HBox();
		hPlayerValue.setAlignment(Pos.CENTER);
		HBox hDealerValue = new HBox();
		hDealerValue.setAlignment(Pos.CENTER);

		VBox vGameInfo = new VBox(10, hGameMoney);
		vGameInfo.setAlignment(Pos.CENTER);

		HBox hGameButtons = new HBox(20, hit, stand);
		hGameButtons.setAlignment(Pos.CENTER);

		VBox vGameButtons = new VBox(10, hGameButtons);
		vGameButtons.setAlignment(Pos.CENTER);

		HBox hGameControls = new HBox(vGameButtons);
		hGameControls.setAlignment(Pos.CENTER);

		HBox hGamePlayer = new HBox(10);
		hGamePlayer.setAlignment(Pos.CENTER);
		HBox hGameDealer = new HBox(10);
		hGameDealer.setAlignment(Pos.CENTER);

		VBox vPlayer = new VBox(10,hGamePlayer, hPlayerValue);
		VBox vDealer = new VBox(10,hDealerValue,hGameDealer);

		HBox hGameWinner = new HBox();
		hGameWinner.setAlignment(Pos.CENTER);

		VBox vCards = new VBox(33, vDealer,hGameWinner, vPlayer);
		vCards.setAlignment(Pos.CENTER);

		VBox vGame = new VBox(20,vGameInfo,vCards,hGameControls);
		vGame.setAlignment(Pos.CENTER);

		BorderPane bpGame = new BorderPane(vGame);
		bpGame.setStyle("-fx-padding: 50px; -fx-background-color: #1E5C3A;");
		Scene sceneGame = new Scene(bpGame, 700, 700 );

		// ************************ END SCENE ************************************

		Text gameFin = new Text("GAME FINISHED");
		gameFin.setStyle("-fx-font-size: 30px; -fx-fill: white;");

		// Buttons for play again and close
		Button bplayAgain = new Button("PLAY AGAIN");
		bplayAgain.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

		Button bcloseGame = new Button("CLOSE");
		bcloseGame.setStyle("-fx-font-size: 18px; -fx-background-color: #F44336; -fx-text-fill: white;");


		HBox hEnd = new HBox(20, bplayAgain, bcloseGame);
		hEnd.setAlignment(Pos.CENTER);


		VBox vEnd = new VBox(50, gameFin, hEnd);
		vEnd.setAlignment(Pos.CENTER);


		BorderPane bpEnd = new BorderPane();
		bpEnd.setCenter(vEnd);
		bpEnd.setStyle("-fx-padding: 100px; -fx-background-color: #1E5C3A;");

		Scene sceneEnd = new Scene(bpEnd, 700, 700);

		Text bet = new Text();
		bet.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-family: sans-serif");
		Text money = new Text();
		money.setStyle("-fx-font-size: 18px; -fx-fill: white; -fx-font-family: sans-serif");
		Text win = new Text();
		win.setStyle("-fx-font-size: 24px; -fx-fill: white; -fx-font-family: sans-serif");
		Text playerValue = new Text();
		playerValue.setStyle("-fx-font-size: 16px; -fx-fill: white; -fx-font-family: sans-serif");
		Text dealerValue = new Text();
		dealerValue.setStyle("-fx-font-size: 16px; -fx-fill: white; -fx-font-family: sans-serif");

		primaryStage.setScene(sceneStart);


		bCloseStart.setOnAction(e -> primaryStage.close());
		bCloseRules.setOnAction(e -> primaryStage.close());
		bCloseMoney.setOnAction(e -> primaryStage.close());
		bCloseBet.setOnAction(	e -> primaryStage.close());
		bcloseGame.setOnAction(	e -> primaryStage.close());

		bStart.setOnAction(e -> primaryStage.setScene(sceneMoney));
		bRules.setOnAction(e -> primaryStage.setScene(sceneRules));
		bBack.setOnAction(e -> primaryStage.setScene(sceneStart));
		exitGame.setOnAction(e->primaryStage.setScene(sceneEnd));

		bplayAgain.setOnAction(e -> {
			tMoney.clear();
			tBet.clear();

			errorLabelMoney.setText("");
			errorLabelBet.setText("");

			hGameMoney.getChildren().clear();
			hGamePlayer.getChildren().clear();
			hDealerValue.getChildren().clear();
			hPlayerValue.getChildren().clear();
			hGameDealer.getChildren().clear();
			hGameWinner.getChildren().clear();

			hit.setDisable(false);
			stand.setDisable(false);
			vGameButtons.getChildren().remove(replay);
			vGameButtons.getChildren().remove(exitGame);


			primaryStage.setScene(sceneMoney);
		});

		replay.setOnAction(e -> {
			tBet.clear();
			errorLabelBet.setText("");

			hGameMoney.getChildren().clear();
			hGamePlayer.getChildren().clear();
			hDealerValue.getChildren().clear();
			hPlayerValue.getChildren().clear();
			hGameDealer.getChildren().clear();
			hGameWinner.getChildren().clear();

			hit.setDisable(false);
			stand.setDisable(false);
			vGameButtons.getChildren().remove(replay);
			vGameButtons.getChildren().remove(exitGame);

			money.setText("Money: $" + Math.round(game.getTotalWinnings()));
			vBet.getChildren().remove(money);
			vBet.getChildren().add(money);

			primaryStage.setScene(sceneBet);

		});

		bMoney.setOnAction(e -> {
			String input = tMoney.getText();
			if (!isDouble(input)) {

				errorLabelMoney.setText("Error: Has to be a number");
			} else {
				double amount = Double.parseDouble(input);
				if (amount <= 0) {

					errorLabelMoney.setText("Error: Has to be more than zero");
				} else {

					game.theDealer.generateDeck();
					game.setTotalWinnings(Double.parseDouble(String.format("%.2f", amount)));
					money.setText("Money: $" + Math.round(game.getTotalWinnings()));
					vBet.getChildren().add(money);
					primaryStage.setScene(sceneBet);
				}
			}
		});

		bBet.setOnAction(e -> {
			String input = tBet.getText();
			if (!isDouble(input)) {

				errorLabelBet.setText("Error: Has to be a number");
			}
			else {
				double amount = Double.parseDouble(input);
				if (amount <= 0) {

					errorLabelBet.setText("Error: Has to be more than zero");
				}
				else if (amount > game.getTotalWinnings()) {

					errorLabelBet.setText("Error: Has to be less than money");
				}
				else {

					game.setCurrentBet(Double.parseDouble(String.format("%.2f",Double.parseDouble(tBet.getText()))));


					game.theDealer.shuffleDeck();
					game.playerHand = game.theDealer.dealHand();
					game.bankerHand = game.theDealer.dealHand();

					money.setText("Money: $" + Math.round(game.getTotalWinnings() - game.getCurrentBet()));
					bet.setText("Bet: $"  + Math.round(game.getCurrentBet()));


					playerValue.setText("Player Value: " + game.gameLogic.handTotal(game.playerHand));

					if(game.bankerHand.get(0).getValue() == 1) {
						dealerValue.setText("Dealer Value: 11" );
					}
					else if (game.bankerHand.get(0).getValue() > 10) {
						dealerValue.setText("Dealer Value: 10" );
					}
					else {
						dealerValue.setText("Dealer Value: " + game.bankerHand.get(0).getValue());
					}

					hGameMoney.getChildren().add(0,money);
					hGameMoney.getChildren().add(1,bet);
					hPlayerValue.getChildren().add(playerValue);
					hDealerValue.getChildren().add(dealerValue);

					playerIndex.set(0);
					bankerIndex.set(0);

					hGamePlayer.getChildren().add(makeImage(getImagePath(game.playerHand.get(playerIndex.getAndIncrement()))));

					hGamePlayer.getChildren().add(makeImage(getImagePath(game.playerHand.get(playerIndex.getAndIncrement()))));

					hGameDealer.getChildren().add(makeImage(getImagePath(game.bankerHand.get(bankerIndex.getAndIncrement()))));

					hGameDealer.getChildren().add(1,makeImage("CBack.png"));

					if(game.gameLogic.handTotal(game.playerHand) == 21) {
						hit.setDisable(true);
						stand.setDisable(true);
						vGameButtons.getChildren().add(1,replay);
						vGameButtons.getChildren().add(2,exitGame);

						game.evaluateWinnings();
						money.setText("Money: $" + Math.round(game.getTotalWinnings()));
						bet.setText("Bet: $0");

						hGameDealer.getChildren().remove(1);
						hGameDealer.getChildren().add(1,makeImage(getImagePath(game.bankerHand.get(bankerIndex.getAndIncrement()))));
						dealerValue.setText("Dealer Value:" + game.gameLogic.handTotal(game.bankerHand));

						win.setText("Blackjack $" + 1.5*game.getCurrentBet());
						hGameWinner.getChildren().add(win);
					}
					primaryStage.setScene(sceneGame);
				}
			}
		});



		hit.setOnAction(e->{

			game.playerHand.add(game.theDealer.drawOne());

			hGamePlayer.getChildren().add(makeImage(getImagePath(game.playerHand.get(playerIndex.getAndIncrement()))));


			playerValue.setText("Player Value: " + game.gameLogic.handTotal(game.playerHand));
			hPlayerValue.getChildren().remove(0);
			hPlayerValue.getChildren().add(0,playerValue);

			if(game.gameLogic.handTotal(game.playerHand) >= 21) {
				hit.setDisable(true);
				stand.setDisable(true);

				if(game.gameLogic.handTotal(game.playerHand) == 21) {
					win.setText("Blackjack $" + 1.5*game.getCurrentBet());
				}
				else if (game.gameLogic.handTotal(game.playerHand) > 21) {
					win.setText("You Lose $" + game.getCurrentBet());
				}
				hGameWinner.getChildren().add(win);

				vGameButtons.getChildren().add(1,replay);
				vGameButtons.getChildren().add(2,exitGame);

				game.evaluateWinnings();
				money.setText("Money: $" + Math.round(game.getTotalWinnings()));
				bet.setText("Bet: $0");
				hGameDealer.getChildren().remove(1);
				hGameDealer.getChildren().add(1,makeImage(getImagePath(game.bankerHand.get(bankerIndex.getAndIncrement()))));
				dealerValue.setText("Dealer Value: " + game.gameLogic.handTotal(game.bankerHand));



				if(game.getTotalWinnings() == 0) {
					hit.setDisable(true);
					stand.setDisable(true);
					vGameButtons.getChildren().remove(replay);
				}
			}


		});

		stand.setOnAction(e->{
			hit.setDisable(true);
			stand.setDisable(true);
			hGameDealer.getChildren( ).remove(1);
			hGameDealer.getChildren().add(1,makeImage(getImagePath(game.bankerHand.get(1))));
			bankerIndex.getAndIncrement();

			if(!game.gameLogic.evaluateBankerDraw(game.bankerHand)) {
				dealerValue.setText("Dealer Value: " + game.gameLogic.handTotal(game.bankerHand));

			}

			while(game.gameLogic.evaluateBankerDraw(game.bankerHand)) {
				game.bankerHand.add(game.theDealer.drawOne());

				hGameDealer.getChildren().add(makeImage(getImagePath(game.bankerHand.get(bankerIndex.getAndIncrement()))));

				dealerValue.setText("Dealer Value: " + game.gameLogic.handTotal(game.bankerHand));

			}

			vGameButtons.getChildren().add(replay);
			vGameButtons.getChildren().add(2,exitGame);

			if(Objects.equals(game.gameLogic.whoWon(game.playerHand, game.bankerHand), "push")) {
				win.setText("Push");
			}
			else if(Objects.equals(game.gameLogic.whoWon(game.playerHand, game.bankerHand), "player")) {
				win.setText("You Win $" + game.getCurrentBet());
			}
			else if(Objects.equals(game.gameLogic.whoWon(game.playerHand, game.bankerHand), "dealer")) {
				win.setText("You Lose $" + game.getCurrentBet());
			}
			hGameWinner.getChildren().add(win);

			game.evaluateWinnings();
			money.setText("Money: $" + Math.round(game.getTotalWinnings()));
			bet.setText("Bet: $0");

			if(game.getTotalWinnings() == 0) {
				hit.setDisable(true);
				stand.setDisable(true);
				vGameButtons.getChildren().remove(replay);
				vGameButtons.getChildren().add(exitGame);
			}

		});


		primaryStage.show();
	}

}
