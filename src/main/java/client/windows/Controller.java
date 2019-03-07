package client.windows;

import javafx.scene.layout.Pane;

/**
 * The type Controller.
 */
public abstract class Controller {

    private Pane pane;

    /**
     * Gets pane.
     *
     * @return the pane
     */
    public final Pane getPane() {
        return pane;
    }

    /**
     * Sets pane.
     *
     * @param pane the pane
     */
    public final void setPane(Pane pane) {
        this.pane = pane;
    }

    /**
     * When the screen is changed this function will be called to update the screen.
     */
    public abstract void update();
}
