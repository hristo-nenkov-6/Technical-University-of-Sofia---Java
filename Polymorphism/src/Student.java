public class Student extends Person{
    private double grade;

    public Student(double grade, String name, int age){
        super(name, age);
        setGrade(grade);
    }

    public double getGrade(){
        return grade;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }

    @Override
    public String introduce(){
        return String.format("%s is %d years old with grade %f",
                getName(),
                getAge(),
                getGrade());
    }

    @Override
       public void printDetails() {
        String output = String.format("Name: %s\n, Age: %d\n, Grade: %d\n", getName(), getAge(), getGrade());
        System.out.println(output);
    }
}