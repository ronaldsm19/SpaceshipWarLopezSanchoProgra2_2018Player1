/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.Pane;

/**
 *
 * @author Ronald Emilio
 */
public interface GUI {
    public Pane init(int s);
    
    public void initializeComponents();

    public void locateComponents();

    public void addEventActions();
    
}
