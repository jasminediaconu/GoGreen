package client.helper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class RowCount {

    /**
     * Count the number of rows in a pane.
     *
     * @param pane GridPane
     * @return numRows
     */
    public static int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if (rowIndex != null) {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }
}
