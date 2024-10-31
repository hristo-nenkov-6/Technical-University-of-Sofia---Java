public class Person implements Printable{
    private String name;
    private int age;

    public Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String introduce(){
        return String.format("%s is %d years old.", name, age);
    }

    @Override
    public void printDetails() {
        String output = String.format("Name: %s\n, Age: %d\n", getName(), getAge());
        System.out.println(output);
    }
}
