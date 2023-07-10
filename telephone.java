//Напишите приложение, которое будет запрашивать у пользователя:
//(1) + фамилия, имя, отчество, телефон. Разделитель - пробел
//(2) + реализовать проверку на количество введенных данных (больше, меньше - ошибка)
//(3) + парсинг данных
//(4) + реализовать проверку данных на форматы: ФИО-строка, телефон-целое число
//(5) + запись в файл в строку <Фамилия><Имя><Отчество><номер_телефона>, однофамильцы - отдельные строки
//(6) закрыть соединение с файлом
//(7) + при возникновении проблемы с чтением-записью в файл - исключение со стектрейсом ошибки

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class telephone{
    public static void main(String[] args) {
        String data = printYourData();
        String[] words = parsing(data);
        checkCountData(words);
        checkFormatData(words);
        writeToFile(dataToFile(words));
    }
    
    public static String printYourData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Фамилию, имя, отчество и телефон через пробел: ");
        String data = in.nextLine();
        in.close();
        return data;
    }

    public static String[] parsing(String data) {
        String [] words = data.split(" ");
        return words;
        }

    public static void checkCountData (String[] words){
        if (words.length!=4) {
            System.out.println("Вы ввели не все запрошенные данные");
        }
    }

    public static void checkFormatData(String[] words){
        try {
            int numberPhoneInt = Integer.parseInt(words[3]);
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число " + e);
        }
    }

    public static String dataToFile(String[] words){
        String info = "<"+words[0]+"> "+"<"+words[1]+"> "+"<"+words[2]+"> "+"<"+Integer.parseInt(words[3])+"> ";
        return info;
    }

    private static void writeToFile(String info) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("telephoneBook.txt", true))){
            writer.write(info);
            writer.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}