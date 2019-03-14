package client.windows;

import javafx.scene.layout.Pane;

/**
 * The type Controller.
 */
public abstract class Controller {

    protected Pane pane;
    protected MainScreenController mainScreenController;

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

    public final void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    /**
     * When the screen is changed this function will be called to update the screen.
     */
    public abstract void update();

    public void init() {
    }
}
