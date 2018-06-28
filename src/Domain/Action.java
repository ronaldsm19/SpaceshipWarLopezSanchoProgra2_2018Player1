/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import org.jdom.Element;

/**
 *
 * @author Ronald Emilio
 */
public class Action {

    private Element eName;
    private Element eCordeElement;

    public Action() {

    }

    public Element create(String name, String cordenadas) {
        //crear elemento con esos atributos y lo retorna
        Element eMessage = new Element("Element");
        eMessage.setAttribute("name", name);
        Element eC = new Element("name");
        eName.addContent(cordenadas);
        eMessage.addContent(eC);
        return eMessage;
    }

}
