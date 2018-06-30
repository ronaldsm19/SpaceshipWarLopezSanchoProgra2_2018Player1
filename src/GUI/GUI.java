package GUI;

import javafx.scene.layout.Pane;

public interface GUI {

    public Pane init(int s);

    public void initializeComponents();

    public void locateComponents();

    public void addEventActions();

}
