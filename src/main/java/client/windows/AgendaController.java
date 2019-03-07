package client.windows;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
public class AgendaController implements Initializable {

    @FXML ScrollPane scrollAgenda;
    @FXML FontAwesomeIcon delete;
    @FXML StackPane stack;

    GridPane gridPane;
    Text date, a,b,c;
    ImageView img, img2, img3;
    JFXButton button, button2, button3;
    JFXDialog dialog;

    /**
     * This function will display a dialog message to the user when he want to delete an activity.
     * The dialog button contains a message and a "delete" button connected to the deleteActivity function.
     */
    private void deleteActivityDialog() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Are you sure?"));
        dialogLayout.setBody(new Text("You are about to delete the activity. Do you want to proceed?"));
        JFXButton del = new JFXButton();
        JFXButton close = new JFXButton();
        del.setOnMouseClicked(e -> deleteActivity());
        close.setOnMouseClicked(e -> dialog.close());
        dialog = new JFXDialog(stack, new Pane(new Text("Are you sure you want to delete this?")), JFXDialog.DialogTransition.TOP);
        dialog.show();

    }

    /**
     * This function will delete the activities from the agenda.
     */
    private void deleteActivity() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String css = "-fx-background-position: 20; -fx-font-size: 28;";
        String css2 = "-fx-font-size: 18;";
        VBox agendaBox = new VBox();

        gridPane = new GridPane();

        agendaBox.setPadding(new Insets(20, 0, 0, 20));

        date = new Text("Fri 9 Mar");

        a = new Text("Bought vegetarian meal");
        b = new Text("Ate vegan food");
        c = new Text("Cycled");
        a.setStyle(css2);
        b.setStyle(css2);
        c.setStyle(css2);

        String path = "/client/windows/images/delete.png";

        img = new ImageView(path);
        img2 = new ImageView(path);
        img3 = new ImageView(path);

        button = new JFXButton("", img);
        button2 = new JFXButton("", img2);
        button3 = new JFXButton("", img3);

        button.setOnMouseClicked(e -> deleteActivityDialog());
        button2.setOnMouseClicked(e -> deleteActivityDialog());
        button3.setOnMouseClicked(e -> deleteActivityDialog());

        date.setStyle(css);
        agendaBox.getChildren().add(date);

        gridPane.setHgap(20);
        gridPane.add(a,1,1);
        gridPane.add(button,7,1);

        gridPane.add(b,1,2);
        gridPane.add(button2,7,2);

        gridPane.add(c,1,3);
        gridPane.add(button3,7,3);

        agendaBox.getChildren().add(gridPane);

        scrollAgenda.setContent(agendaBox);
        agendaBox.setSpacing(15);

        JFXButton ssbutton1 = new JFXButton("R1");
        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);

//        JFXButton ssbutton2 = new JFXButton("R2");
//        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
//
//        JFXButton ssbutton3 = new JFXButton("R3");
//        ssbutton1.setButtonType(JFXButton.ButtonType.RAISED);
//
//        JFXNodesList nodesList = new JFXNodesList();

//        nodesList.addAnimatedNode(ssbutton1);
//        nodesList.addAnimatedNode(ssbutton2);
//        nodesList.addAnimatedNode(ssbutton3);
    }
}
