package org.lab1;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Strelchenko on 23.09.2015.
 */
public class Main {

    static boolean menu() {
        Scanner sc = new Scanner(System.in);
        int number;
        System.out.println("1. Добавить пациента");
        System.out.println("2. Добавить посетителя");
        System.out.println("3. Изменить данные о пациенте");
        System.out.println("4. Удалить пациента");
        System.out.println("5. Вывод даных о пациенте");
        System.out.println("6. Вывод всех данных о пациенте");
        System.out.println("7. Выход");

        System.out.print("Введите целое число: ");
        number = sc.nextInt();
        switch (number) {
            case 1:
                System.out.println("Ввод данных");
                System.out.println("Имя пациента Федорченко И.И.");
                System.out.println("Палата 6-А");
                DataBase.WriteDB("patient","Федорченко И.И.", "6-А");
                return false;
            case 2:
                System.out.println("Имя посетителя Федорченко М.И.");
                System.out.println("Id пациет 1");
                System.out.println("Дата 12/06/13");
                DataBase.WriteDB("visited","1", "Федорченко И.И.", "12/06/13");
                System.out.println();
                return false;
            case 3:
                System.out.println("Изменить данные о пациенте");
                System.out.println("Имя Федопр О.П.");
                System.out.println("ID 8");
                DataBase.UpdateDB("patient", "Федопр О.П.", 8);
                return false;
            case 4:
                System.out.println("Пациент Федорченко И.И.");
                System.out.println("ID 8");
                DataBase.DeleteDB("patient", 8);
                return false;
            case 5:
                DataBase.ReadDB("patient", "Федорченко И.И.");
                return false;
            case 6:
                DataBase.ReadDB("patient");
                return false;
            default:
                return true;
        }


    }


    public static void main(String[] args) {

        DataBase.Conn();

        while (!menu()) {
            System.out.println("-----------------------------------------------------------------------------------");
        }
        try {
            DataBase.CloseDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
