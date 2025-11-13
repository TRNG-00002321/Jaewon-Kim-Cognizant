package note;

public class Student {
    String name;
    static int count = 0;
    int nonstatic = 0;

    public Student(String name){
        this.name=name;
        count++;
        nonstatic++;
    }   

    public static void main(String[] args) {
        Student s1 = new Student("s1");
        System.out.println("Static count: "+Student.count+"Nonstaic: "+s1.nonstatic);
        Student s2 = new Student("s2");
        System.out.println("Static count: "+Student.count+"Nonstaic: "+s2.nonstatic);
    }
}
