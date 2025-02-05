package student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            String name = sc.nextLine();
            String facNumber = sc.nextLine();

            try {
                Student student = new Student(name, facNumber);
            }
            catch (StudentException se){
                System.out.println(se.getMessage());
                continue;
            }

            break;
        }
    }
}