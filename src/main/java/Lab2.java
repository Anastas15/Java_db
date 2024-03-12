import java.util.Scanner;

public class Lab2 {

    public static void lab2() {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database(2);

        String string1 = "";
        String string2 = "";

        while (true) {
            System.out.println("Выберите опцию:\n 1. Показать таблицы MySQL\n 2. Создать таблицу MySQL\n 3. Ввести строки и сохранить\n 4. Подсчитать длины и сохранить\n 5. Конкатенировать и сохранить\n 6. Сравнить и сохранить\n 7. Экспортировать в Excel\n 8. Выйти");
            int choice = scanner.nextInt();
            if (choice == 8) break;

            String tableName = "";


            if (choice > 1 && choice < 8) {
                System.out.println("Введите название таблицы: ");
                tableName = scanner.next();
            }
            switch (choice) {
                case 1:
                    db.showTables();
                    break;
                case 2:
                    db.createStringTable(tableName);
                    break;
                case 3:
                    System.out.println("Введите первую строку: ");
                    string1 = scanner.next();
                    System.out.println("Введите вторую строку: ");
                    string2 = scanner.next();
                    db.insertStringData(tableName, string1, string2, 0, 0, "", "");
                    break;
                    //String.valueOf()
                case 4:
                    db.insertStringData(tableName, string1, string2, string1.length(), string2.length(), "", "");
                    break;
                case 5:
                    db.insertStringData(tableName, string1, string2, 0, 0, string1 + string2, "");
                    break;
                case 6:
                    db.insertStringData(tableName, string1, string2, 0, 0, "", String.valueOf(string1.equals(string2)));
                    break;
                case 7:
                    db.exportToExcel(tableName);
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }
}