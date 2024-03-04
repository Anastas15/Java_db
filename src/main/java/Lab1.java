import java.util.Scanner;

public class Lab1 {
    public static void lab1() {
        Scanner scanner = new Scanner(System.in);

        MathOperations math = new MathOperations();
        Database db = new Database();


        while (true) {
            System.out.println("Выбрать опцию: 1. Вывести все таблицы из MySQL 2.Создать таблицу в MySQL 3. Сложение 4. Вычитание 5. Умножение 6. Деление 7. Остаток 8. Модуль 9. Возвести в степень второго числа  10. Сохранить все данные в Excel 11. Выйти");
            int choice = scanner.nextInt();
            if (choice == 11) break;
            double num1 = 0;
            double num2 = 0;
            if (choice > 2 && choice < 10) {
                System.out.println("Введите первое число: ");
                num1 = scanner.nextDouble();
                System.out.println("Введите второе число: ");
                num2 = scanner.nextDouble();
            }

            String tableName = "";
            double result = 0;

            switch (choice) {
                // call show tables
                case 1:
                    db.showTables();
                    break;
                // Implement create tables
                case 2:
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.createTable(tableName);
                    break;
                case 3:
                    result = math.add(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Addition", tableName, num1, num2, result);
                    System.out.println(tableName);
                    break;
                case 4:
                    result = math.subtract(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Subtraction", tableName, num1, num2, result);
                    break;
                case 5:
                    result = math.multiply(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Multiplication", tableName, num1, num2, result);
                    break;
                case 6:
                    result = math.quotient(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Division", tableName, num1, num2, result);
                    break;
                case 7:
                    result = math.remainder(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Remainder", tableName, num1, num2, result);
                    break;
                case 8:
                    result = math.num1Absolute(num1);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.saveAbs("Num1Absolute", tableName, num1, result);
                    break;
                case 9:
                    result = math.num1PowerNum2(num1, num2);
                    System.out.println("Введите название таблицы: ");
                    tableName = scanner.next();
                    db.save("Num1PowerNum2", tableName, num1, num2, result);
                    break;
                case 10:
                    // call excel func
                    System.out.println("Введите название таблицы: ");
                    String tableNameForExport = scanner.next();
                    db.exportToExcel(tableNameForExport);
                    break;


            }

            System.out.println("Результат: " + result);
        }
    }
}
