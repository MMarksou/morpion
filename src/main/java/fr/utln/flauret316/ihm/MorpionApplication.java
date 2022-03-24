package fr.utln.flauret316.ihm;

import fr.utln.flauret316.model.MorpionModel;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Une application de Morpion
 * @author Fabien LAURET
 */
public class MorpionApplication extends Application {

   private final Label  joueur1;

   private final Label score1;

   private final Label score2;

   private final Label  joueur2;

   private final Label victoire;

   private final Label partie;

   private final Label nbPartie;

   private final Button btnPartie;

   private final Button btnRecom;

   private final HBox jscore1;

   private final HBox jscore2;

   private final FlowPane menu;

   private final TilePane grille;

   private final BorderPane disposition;

   private final Scene scene;

   private MorpionModel model;

   public MorpionApplication() {
      this.joueur1 = new Label("Joueur 1");

      this.joueur2 = new Label("Joueur 2");

      this.partie = new Label("Partie ");

      this.score1 = new Label("0");

      this.score2 = new Label("0");

      this.nbPartie = new Label("0");

      this.jscore1 = new HBox(joueur1,score1);

      this.jscore2 = new HBox(joueur2,score2);

      HBox pnbpartie = new HBox(partie, nbPartie);

      this.btnPartie = new Button("Paramètre de la partie");

      this.btnRecom = new Button("Relancer une partie");

      btnRecom.setDisable(true);

      HBox btn = new HBox(btnPartie, btnRecom);

      this.victoire = new Label();

      this.menu = new FlowPane(10,10, btn, jscore1, jscore2, pnbpartie,victoire);

      this.grille = new TilePane();

      this.disposition = new BorderPane();

      this.scene = new Scene(disposition, 1080, 720);
   }

   public Label getVictoire() {
      return victoire;
   }

   public Label getScore1() {
      return score1;
   }

   public Label getScore2() {
      return score2;
   }

   public TilePane getGrille() {
      return grille;
   }

   public Label getJoueur1() {
      return joueur1;
   }

   public Label getJoueur2() {
      return joueur2;
   }

   public Label getNbPartie() {
      return nbPartie;
   }

   public void setModel(MorpionModel model) {
      this.model = model;
   }

   @Override
   public void start(Stage stage) {

      joueur1.setFont(Font.font(20));
      joueur1.setTextFill(Color.CHOCOLATE);
      joueur1.setAlignment(Pos.CENTER_LEFT);
      joueur1.setMinSize(150,100);
      joueur1.setMaxSize(150,100);
      joueur2.setFont(Font.font(20));
      joueur2.setTextFill(Color.CHOCOLATE);
      joueur2.setAlignment(Pos.CENTER_LEFT);
      joueur2.setMinSize(150,100);
      joueur2.setMaxSize(150,100);
      partie.setFont(Font.font(20));
      partie.setTextFill(Color.CHOCOLATE);
      partie.setAlignment(Pos.CENTER_LEFT);
      partie.setMinSize(150,100);
      partie.setMaxSize(150,100);
      score1.setFont(Font.font(20));
      score1.setTextFill(Color.CHOCOLATE);
      score1.setAlignment(Pos.CENTER_RIGHT);
      score2.setFont(Font.font(20));
      score2.setTextFill(Color.CHOCOLATE);
      score2.setAlignment(Pos.CENTER_RIGHT);
      nbPartie.setFont(Font.font(20));
      nbPartie.setTextFill(Color.CHOCOLATE);
      nbPartie.setAlignment(Pos.CENTER_RIGHT);
      jscore1.setAlignment(Pos.CENTER_LEFT);
      jscore2.setAlignment(Pos.CENTER_LEFT);
      nbPartie.setAlignment(Pos.CENTER_LEFT);
      nbPartie.setMaxSize(150,100);
      btnPartie.setOnAction(evt -> {
         paramJeu();
         btnRecom.setDisable(true);
      });
      btnRecom.setOnAction(evt ->
      {
         getGrille().getChildren().clear();
         getVictoire().visibleProperty().setValue(false);
         setModel(new MorpionModel(model.getJoueur1(),model.getJoueur2(), model.getGrille().length));
         getGrille().setDisable(false);
         createGrille();
         Integer partie = Integer.parseInt(getNbPartie().getText())+1;
         getNbPartie().setText(partie.toString());
         btnRecom.setDisable(true);
      });
      menu.setAlignment(Pos.TOP_CENTER);
      menu.setOrientation(Orientation.VERTICAL);
      disposition.setRight(menu);
      disposition.setLeft(grille);
      disposition.setStyle("-fx-background-color: #FAEBD7");

      stage.setTitle("Morpion");
      stage.setScene(scene);
      stage.setResizable(false);

      grille.setOnMouseClicked(evt -> {Rectangle rect = (Rectangle) evt.getPickResult().getIntersectedNode();
         int x = (int) rect.getX();
         int y = (int) rect.getY();
         majGrille(x,y);
         });

      stage.show();
   }

   /**
    * Fenêtre pour les paramètres de la partie : nom des joueurs et taille de la grille
    */
   public void paramJeu() {
      Label  lblTaille = new Label("Taille de la grille :");
      Label  lblJoueur1 = new Label("Nom du joueur 1 :");
      Label  lblJoueur2 = new Label("Nom du joueur 2 :");
      TextField txtTaille = new TextField();
      TextField txtJoueur1 = new TextField();
      TextField txtJoueur2 = new TextField();
      txtJoueur1.setText(getJoueur1().getText());
      txtJoueur2.setText(getJoueur2().getText());
      HBox hbTaille = new HBox(3, lblTaille, txtTaille);
      HBox hbJoueur1 = new HBox(3, lblJoueur1, txtJoueur1);
      HBox hbJoueur2 = new HBox(3, lblJoueur2, txtJoueur2);
      FlowPane paramPartie = new FlowPane(10,10, hbTaille,hbJoueur1,hbJoueur2);
      Button confirmer = new Button("Confirmer");
      Button annuler = new Button("Annuler");
      FlowPane boutons = new FlowPane(10,10,confirmer,annuler);
      BorderPane disposition2 = new BorderPane();
      Scene param = new Scene(disposition2,320,300);

      paramPartie.setOrientation(Orientation.VERTICAL);
      paramPartie.setPadding(new Insets(10));

      boutons.setAlignment(Pos.CENTER);
      disposition2.setBottom(boutons);
      disposition2.setLeft(paramPartie);
      disposition2.setStyle("-fx-background-color: #FAEBD7");

      Stage fenetreParam = new Stage();

      fenetreParam.setTitle("Paramètre de la partie");
      fenetreParam.setScene(param);

      confirmer.setOnAction(actionEvent -> {
         getGrille().getChildren().clear();
         getVictoire().visibleProperty().setValue(false);
         getGrille().setDisable(false);
         generationPartie(txtJoueur1.getText(), txtJoueur2.getText());
         setModel(new MorpionModel(txtJoueur1.getText(),txtJoueur2.getText(), Integer.parseInt(txtTaille.getText())));
         createGrille();
         Integer partie = Integer.parseInt(getNbPartie().getText())+1;
         getNbPartie().setText(partie.toString());
         fenetreParam.close();});

      annuler.setOnAction(evt -> fenetreParam.close());

      fenetreParam.show();
   }

   /**
    * Affichage des noms de joueurs
    * @param joueur1 le nom du joueur1 rentré dans le textfield
    * @param joueur2 le nom du joueur2 rentré dans le textfield
    */
   public void generationPartie(String joueur1, String joueur2){
      getJoueur1().setText(joueur1);
      getJoueur2().setText(joueur2);
   }

   /**
    * Génère la grille et la complète selon le tableau du modele (correspondant à l'état actuel du jeu).
    */
   public void createGrille(){
      int taille = model.getGrille().length;
      Integer[][] grid = model.getGrille();

      getGrille().setPrefColumns(taille);
      getGrille().setPrefTileHeight(720f/taille);
      getGrille().setPrefTileWidth(720f/taille);
      getGrille().setAlignment(Pos.TOP_CENTER);

      for(int i = 0; i < taille; i++) {
         for (int j = 0; j < taille; j++) {
            if(grid[i][j] == 0) {
               Rectangle rect = new Rectangle(720f / taille, 720f / taille);
               rect.setFill(Color.TRANSPARENT);
               rect.setStroke(Color.BLACK);
               rect.setX(i);
               rect.setY(j);
               getGrille().getChildren().add(rect);
            }
            if(grid[i][j] == 1) {
               StackPane sp = new StackPane();

               Rectangle rect = new Rectangle(720f / taille, 720f / taille);
               rect.setFill(Color.TRANSPARENT);
               rect.setStroke(Color.BLACK);
               rect.setX(i);
               rect.setY(j);

               Circle circle = new Circle(((720f / taille)/2)-10);
               circle.setFill(Color.WHITE);

               sp.getChildren().addAll(rect, circle);

               getGrille().getChildren().add(sp);
            }

            if (grid[i][j] == 2) {
               StackPane sp1 = new StackPane();

               Rectangle rect = new Rectangle(720f / taille, 720f / taille);
               rect.setFill(Color.TRANSPARENT);
               rect.setStroke(Color.BLACK);
               rect.setX(i);
               rect.setY(j);

               Rectangle rect1 = new Rectangle(20, 720f / taille);
               rect1.setFill(Color.WHITE);
               rect1.setRotate(45);

               Rectangle rect2 = new Rectangle(20, 720f / taille);
               rect2.setFill(Color.WHITE);
               rect2.setRotate(-45);

               sp1.getChildren().addAll(rect, rect1, rect2);

               getGrille().getChildren().add(taille*i+j,sp1);
            }
         }
      }
   }

   /**
    * Mise à jour de la grille et de l'écran en cas de victoire ou de match nul
    * @param x coord x de la case cliquée
    * @param y coord y de la case cliquée
    */

   public void majGrille(int x, int y){
      int event = model.jouer(x,y);

      getGrille().getChildren().clear();
      createGrille();

      if(event == 1){
         getVictoire().visibleProperty().setValue(true);
         getVictoire().setText("Victoire de "+model.getJoueur1());
         getVictoire().setFont(Font.font(20));
         getVictoire().setTextFill(Color.CHOCOLATE);
         getVictoire().setMaxSize(250,100);
         Integer score = Integer.parseInt(getScore1().getText())+1;
         getScore1().setText(score.toString());
         btnRecom.setDisable(false);
         getGrille().setDisable(true);

      }
      if(event == 2){
         getVictoire().visibleProperty().setValue(true);
         getVictoire().setText("Victoire de "+model.getJoueur2());
         getVictoire().setFont(Font.font(20));
         getVictoire().setTextFill(Color.CHOCOLATE);
         getVictoire().setMaxSize(250,100);
         Integer score = Integer.parseInt(getScore2().getText())+1;
         getScore2().setText(score.toString());
         btnRecom.setDisable(false);
         getGrille().setDisable(true);

      }
      if (event == 3){
         getVictoire().visibleProperty().setValue(true);
         getVictoire().setText("Match nul");
         getVictoire().setFont(Font.font(20));
         getVictoire().setTextFill(Color.CHOCOLATE);
         getVictoire().setMaxSize(250,100);
         btnRecom.setDisable(false);
         getGrille().setDisable(true);
      }
   }
}