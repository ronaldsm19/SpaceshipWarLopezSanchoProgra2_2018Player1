package Domain;

//author @Kenneth Lopez Porras
public class Student {

    private String name;
    private String identification;
    private float admissionGrade;

    //constructor}
    public Student() {
        this.name = "";
        this.identification = "";
        this.admissionGrade = 0;
    }

    public Student(String name, String identification, float admissionGrade) {
        this.name = name;
        this.identification = identification;
        this.admissionGrade = admissionGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public float getAdmissionGrade() {
        return admissionGrade;
    }

    public void setAdmissionGrade(float admissionGrade) {
        this.admissionGrade = admissionGrade;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", identification=" + identification + ", admissionGrade=" + admissionGrade + '}';
    }

}
