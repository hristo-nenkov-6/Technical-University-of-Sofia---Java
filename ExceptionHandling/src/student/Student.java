package student;

public class Student {
    private String name;
    private String facultyNumber;

    public Student(String name, String facultyNumber) throws StudentException {
        setName(name);
        setFacultyNumber(facultyNumber);
    }

    public String getName(){
        return this.name;
    }

    public String getFacultyNumber(){
        return this.facultyNumber;
    }

    private void setName(String name) throws StudentException
    {
        if(getName().isEmpty() || getName().isBlank()) throw new StudentException("Name is not completed");
        this.name = name;
    }

    private void setFacultyNumber(String facultyNumber)throws StudentException{
        if(getFacultyNumber().length() != 10)   throw new StudentException("Faculty number is not completed");
        this.facultyNumber = facultyNumber;
    }

}

