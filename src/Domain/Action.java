package Domain;

import org.jdom.Element;

public class Action {

    private Element eName;
    private Element eCordeElement;

    public Action() {

    }

    public Element create(String name, String cordenadas) {
        Element eMessage = new Element("Element");
        eMessage.setAttribute("name", name);
        Element eC = new Element("name");
        eName.addContent(cordenadas);
        eMessage.addContent(eC);
        return eMessage;
    }

}
