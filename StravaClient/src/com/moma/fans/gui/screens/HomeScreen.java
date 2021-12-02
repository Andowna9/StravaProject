package com.moma.fans.gui.screens;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.moma.fans.controllers.ChallengeController;
import com.moma.fans.controllers.TrainingSessionController;
import com.moma.fans.controllers.UserController;
import com.moma.fans.data.dto.challenge.ChallengeDTO;
import com.moma.fans.data.dto.session.TrainingSessionDTO;
import com.moma.fans.gui.Screen;
import com.moma.fans.gui.ScreenController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

/**
 * Vista con panel principal de opciones.
 * @author JonanC
 * @author UnaiCL
 */
public class HomeScreen implements Screen {

	private UserController userController;
    private TrainingSessionController trainingSessionController;
    private ChallengeController challengeController;

    private TableView<TrainingSessionDTO> tblSessions;
    private Text tUser;

    private ListView<ChallengeDTO> createdChallenges;
    private ListView<ChallengeDTO> availableChallenges;

    private Parent view;
	
    public HomeScreen(UserController userController, TrainingSessionController trainingSessionController, ChallengeController challengeController) {

        this.userController = userController;
        this.trainingSessionController = trainingSessionController;
        this.challengeController = challengeController;
    	
    	view = createView();

    }

    private Parent createView() {

        VBox root = new VBox();

        // Cerrar sesión (Parte superior)
        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_RIGHT);
        Hyperlink hlLogout = new Hyperlink("Cerrar sesión");
        hlLogout.setBorder(Border.EMPTY);
        hlLogout.setPadding(new Insets(10, 21, 0, 10));
        topControls.getChildren().add(hlLogout);

        // Texto superior, dedicado a cada usuario
        TextFlow tFlow = new TextFlow();
        Text t1 = new Text("Panel de ");
        tUser = new Text();
        tUser.setStyle("-fx-font-weight: bold");
        tFlow.getChildren().addAll(t1, tUser);
        tFlow.setTextAlignment(TextAlignment.CENTER);
        tFlow.setPadding(new Insets(0, 0, 15, 0));

        /*
         * |--------------------------------------------|
         *  Creación del splitpane
         */

        SplitPane splitPane = new SplitPane();

        // Creación y configuración de las cajas
        VBox sessionsBox = new VBox();
        VBox challengesBox = new VBox();

        sessionsBox.setAlignment(Pos.CENTER);
        sessionsBox.setPadding(new Insets(0, 0, 12, 0));
        sessionsBox.setSpacing(12);

        challengesBox.setAlignment(Pos.CENTER);
        challengesBox.setPadding(new Insets(0, 0, 12, 0));
        challengesBox.setSpacing(12);

        // Panel de sesiones
        tblSessions = new TableView<>();
        tblSessions.setPlaceholder(new Label("No hay sesiones de entrenamiento registradas"));

        TableColumn<TrainingSessionDTO, String> colTitle = new TableColumn<>("Título");
        TableColumn<TrainingSessionDTO, String> colSport = new TableColumn<>("Deporte");
        TableColumn<TrainingSessionDTO, Float> colDistance = new TableColumn<>("Distancia");
        TableColumn<TrainingSessionDTO, LocalDateTime> colDateTime = new TableColumn<>("Fecha/hora");
        TableColumn<TrainingSessionDTO, Duration> colDuration = new TableColumn<>("Tiempo total");

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colSport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));

        colDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colDateTime.setCellFactory((tableColumn) -> {

            TableCell<TrainingSessionDTO, LocalDateTime> tableCell = new TableCell<>() {

                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {

                    super.updateItem(item, empty);

                    if (item == null || empty) {

                        this.setText(null);
                    }

                    else {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        this.setText(item.format(formatter));

                    }

                }

            };

            return tableCell;

        });
        colDateTime.setPrefWidth(125);

        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDuration.setCellFactory((tableColumn)-> {
            TableCell<TrainingSessionDTO, Duration> tableCell = new TableCell<>() {

                @Override
                protected void updateItem(Duration item, boolean empty) {

                    super.updateItem(item, empty);

                    if (item == null || empty) {

                        this.setText(null);
                    }

                    else {

                        long HH = item.toHours();
                        long mm = item.toMinutesPart();
                        this.setText(String.format("%02d h %02d min", HH, mm));

                    }

                }

            };

            return tableCell;
        });

        // Warning: Unchecked generic array creation for varargs
        // tblSessions.getColumns().addAll(colTitle, colSport, colDistance, colDateTime, colDuration);

        // Alternativa

        tblSessions.getColumns().add(colTitle);
        tblSessions.getColumns().add(colSport);
        tblSessions.getColumns().add(colDistance);
        tblSessions.getColumns().add(colDateTime);
        tblSessions.getColumns().add(colDuration);

        tblSessions.getColumns().stream().forEach(tableColumn -> tableColumn.setStyle("-fx-alignment: CENTER"));

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab createdChallengesTab = new Tab("Mis retos");
        Tab acceptedChallengesTab = new Tab("Retos aceptados");
        Tab availableChallengesTab = new Tab("Retos disponibles");

        createdChallenges = new ListView<>();
        createdChallengesTab.setContent(createdChallenges);

        ListView<String> acceptedChallenges = new ListView<>();
        acceptedChallengesTab.setContent(acceptedChallenges);

        availableChallenges = new ListView<>();
        availableChallengesTab.setContent(availableChallenges);

        createdChallenges.setCellFactory(new Callback<ListView<ChallengeDTO>, ListCell<ChallengeDTO>>() {
            @Override
            public ListCell<ChallengeDTO> call(ListView<ChallengeDTO> param) {
                 ListCell<ChallengeDTO> cell = new ListCell<ChallengeDTO>() {
                     @Override
                    protected void updateItem(ChallengeDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item.getTitle() + " " + item.getStartDate()
                                    + " " + item.getEndDate() + " " + item.getSport().toUpperCase());
                        } else {
                            setText(null);
                        }
                    }
                 };
                return cell;
            }
        });
        
        availableChallenges.setCellFactory(new Callback<ListView<ChallengeDTO>, ListCell<ChallengeDTO>>() {
            @Override
            public ListCell<ChallengeDTO> call(ListView<ChallengeDTO> param) {
                 ListCell<ChallengeDTO> cell = new ListCell<ChallengeDTO>() {
                     @Override
                    protected void updateItem(ChallengeDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item.getTitle() + " " + item.getStartDate()
                                    + " " + item.getEndDate() + " " + item.getSport().toUpperCase());
                        } else {
                            setText(null);
                        }
                    }
                 };
                return cell;
            }
        });
        
        tabPane.getTabs().add(createdChallengesTab);
        //tabPane.getTabs().add(acceptedChallengesTab); TODO
        tabPane.getTabs().add(availableChallengesTab);

        // Botones
        Button btnCreateSession = new Button("Crear sesión de entrenamiento");
        Button btnCreateChallenge = new Button("Crear reto");

        // Añadir elementos a las VBox
        sessionsBox.getChildren().addAll(tblSessions, btnCreateSession);
        challengesBox.getChildren().addAll(tabPane, btnCreateChallenge);

        /*
         * |--------------------------------------------|
         *  FIN Creación del splitpane
         */

        // Añadir las VBox a los paneles
        splitPane.getItems().addAll(sessionsBox, challengesBox);


        root.getChildren().addAll(topControls, tFlow, splitPane);

        // Eventos |----------------------------------|
        btnCreateSession.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.TRAINING_SESSION_CREATION));
        btnCreateChallenge.setOnAction(event -> ScreenController.getInstance().setScreen(ScreenController.State.CHALLENGE_CREATION));
        hlLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);

                try {
                    userController.logout();
                    ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);
                } catch (RemoteException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error al iniciar sesión");
                    alert.setContentText(e.getCause().getMessage());
                    alert.showAndWait();
                }
            }
        });

        return root;
    }

    private void updateSessions() {

        tblSessions.getItems().clear();
        List<TrainingSessionDTO> trainingSessions = trainingSessionController.getTrainingSessions(userController.getToken());
        tblSessions.getItems().addAll(trainingSessions);

    }

    private void updateChallenges() {

        createdChallenges.getItems().clear();
        availableChallenges.getItems().clear();

        // TODO Utilizar un renderer más apropiado
        // https://www.baeldung.com/javafx-listview-display-custom-items
        // https://www.turais.de/how-to-custom-listview-cell-in-javafx/

        for (ChallengeDTO ch : challengeController.getCreatedChallenges(userController.getToken())) {

            createdChallenges.getItems().add(ch);
        }

        for (ChallengeDTO ch : challengeController.getAvailableChallenges(userController.getToken())) {

        	availableChallenges.getItems().add(ch);
        }
        
    }

    @Override
    public void initialize() {

        tUser.setText("@" + userController.getUserData().getNickname());
        updateSessions();
        updateChallenges();
    }

    @Override
    public Parent getView() {
        return view;
    }
}
