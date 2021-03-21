package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {

    @FXML
    private TextArea txtOutput;

    @FXML
    public BorderPane root;

    @FXML
    private MenuItem item_Open;

    @FXML
    private MenuItem item_Save;

    @FXML
    private MenuItem item_Exit;

    @FXML
    public void setCambria(ActionEvent event) {
        txtOutput.setStyle("-fx-font-family: Cambria;");
    }

    @FXML
    public void setArial(ActionEvent event) {
        txtOutput.setStyle("-fx-font-family: Arial;");
    }

    @FXML
    public void fontSmall(ActionEvent event) {txtOutput.setStyle("-fx-font-size: 10;");}

    @FXML
    public void fontMiddle(ActionEvent event) {txtOutput.setStyle("-fx-font-size: 20;");}

    @FXML
    public void fontLarge(ActionEvent event) {txtOutput.setStyle("-fx-font-size: 30;");}


    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setHeaderText("This is a Text editor. You can customize your text.");
        alert.show();
    }

    @FXML
    private void onUniversity() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setHeaderText("The program was built and designed by a freshman\n" +
                "at Computer Science Department in Ala-Too International University in 2021. ");
        alert.show();
    }

    @FXML
    private void setItem_Save(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);


        if (file!=null){
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(txtOutput.getText());
            } catch (Exception e) {
                e.printStackTrace();
                handleExceptionAlert();
            } finally {
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        handleExceptionAlert();
                    }
                }
            }
        }
    }

    @FXML
    public void handleExceptionAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("You are stupid!");
        alert.show();

    }


    @FXML
    public void clickOnOpen(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open your file, please");
        File selectedFile = chooser.showOpenDialog(stage);
        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
        BufferedReader BR = new BufferedReader(FR);
        StringBuilder sb = new StringBuilder();
        String myText = "";

        while((myText = BR.readLine()) != null) {
            sb.append(myText).append("\n");
        }
        txtOutput.setText(sb.toString());
    }

    @FXML
    public void clickOnExit(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    private void handlePaste(ActionEvent event){
        txtOutput.paste();
    }
    @FXML
    private void handleCopy(ActionEvent event){
        txtOutput.copy();
    }
    @FXML
    private void handleCut(ActionEvent event){
        txtOutput.cut();
    }
    @FXML
    private void handleUndo(ActionEvent event){
        txtOutput.undo();
    }
}
