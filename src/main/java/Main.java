import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер лабараторной (1-4):");
        int labNumber = scanner.nextInt();

        switch (labNumber) {
            case 1:
                Lab1.lab1();
                break;
//            case 2:
//                Lab2.Lab2();
//                break;
//            case 3:
//                Lab3.run();
//                break;
//            case 4:
//                Lab4.run();
//                break;
            default:
                System.out.println("Неправильное число.");
        }
        scanner.close();
    }
}
