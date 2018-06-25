package Data;

import Domain.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

//author @Kenneth Lopez Porras
public class XMLStudentManager {
    private Document document;
    private Element root;
    private String path;
    
    public XMLStudentManager(String path) throws JDOMException, IOException{
        this.path = path;
        File fileStudent = new File(this.path);
        if(fileStudent.exists()){
            //1. EL ARCHIVO YA EXISTE, ENTONCES LO CARGO DE MEMORIA
            
            //toma la estructura de datos y los carga en memoria
            SAXBuilder saxBuidelr = new SAXBuilder();
            saxBuidelr.setIgnoringElementContentWhitespace(true);
            
            //cargar en memoria
            this.document = saxBuidelr.build(this.path);
            this.root = this.document.getRootElement();
        }else{
            //2.EL NO EXISTE, ENTONCES LO CREA Y LO CARGA EN MEMORIA
            
            //CRAMOS EL ELEMENTO RAIZ
            this.root = new Element("students");
            
            //creamos el documento
            this.document = new Document(this.root);
            
            //AHORA GUARDAMOS EL DOCUMENTO
            storeXML();
        }
    }//END METHOD
    //Almacenamos en disco duro nuestro documento xml en la ruta especifica
    private void storeXML() throws FileNotFoundException, IOException {
        XMLOutputter xMLOutputter = new XMLOutputter();
        xMLOutputter.output(this.document,new PrintWriter(this.path));
    }
    //metodo para insertar un estudiante nuevo
    public void insertarStudent(Student student) throws IOException{
        //insertamos en el documento en memoria
        
        //creamos el student
        Element eStudent = new Element("student");
        //agregamos un atributo
        eStudent.setAttribute("identification",student.getIdentification());
        
        //crear nombre
        Element eName = new Element("name");
        eName.addContent(student.getName());
        //crear el elemento nota
        Element eAdmissionGrade = new Element("admissionGrade");
        eAdmissionGrade.addContent(String.valueOf(student.getAdmissionGrade()));
        
        //agregar al elemento student
        eStudent.addContent(eName);
        eStudent.addContent(eAdmissionGrade);
        
        //agregar a root
        this.root.addContent(eStudent);
        
        //guarde todo
        storeXML();
    }
    //metodo para obtener todos los objetos del xml
    public Student[] getAllStudents(){
        //obtenemos la cantidad de estudiates
        int studentsQuantity = this.root.getContentSize();
        Student[] studentsArray = new Student[studentsQuantity];
        
        //obtenemos una lista con todos los elementos del root
        List elementList = this.root.getChildren();
        
        //recorrer la lista para ir creando el arreglo
        int count =0;
        //for each
        for(Object currentObject : elementList){
            //transformo de objeto a elemento
            Element currentElement = (Element)currentObject;
            
            //crear el estudiante
            Student currentStudent = new Student();
            //establezco el id
            currentStudent.setIdentification(
                    currentElement.getAttributeValue("identification")
            );
            //establezco el nombre
            currentStudent.setName(
                    currentElement.getChild("name").getValue()
            );
            currentStudent.setAdmissionGrade(
                    Float.parseFloat(currentElement.getChild("admissionGrade").getValue())
            );
            studentsArray[count++] = currentStudent;
        }//end for
        return studentsArray;
    }
    public boolean delete(String identification) throws IOException {
        List listElementos = this.root.getChildren();
        int cont = 0;
        for (Object objectActual : listElementos) {
            Element elementoActual = (Element) objectActual;
            if (elementoActual.getAttributeValue("identification").equals(identification)) {
                this.root.removeContent(cont);
                storeXML();
                return true;
            }
            cont++;
        }
        return false;
    }
}
