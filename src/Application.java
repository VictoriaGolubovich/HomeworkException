import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public String dataInput(){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Введите через запятую следующие данные:\n" +
                        "фамилия имя отчество - через пробел\n" +
                        "дата рождения - формата dd.mm.yyyy\n" +
                "номер телефона - целое беззнаковое число (11 цифр)\n" +
                "пол - символ латиницей f или m");
        return scanner.nextLine();
    }
    public String[] dataSeparation(String data){
        String[] dataSep = data.split(", ");
        return dataSep;
    }

    public void dataFormatting() throws IOException {
        String s = dataInput();
        String[] array = dataSeparation(s);
        if (array.length != 4){
            throw new MyException("Введены некорректные данные");
        } else if(array[1].length() != 10){
            throw new MyException("Введена неверная дата рождения");
        } else if(array[2].length() != 11){
            throw new MyException("Введен неверный номер телефона");
        } else if(array[3].length() != 1){
            throw new MyException("Введен неверно пол");
        }
        String[] name = array[0].split(" ");
        System.out.println(name.length);
        if(name.length != 3) {
            throw new MyException("Введено некорректное ФИО");
        }
        for (int i = 0; i < name.length; i++) {
            if(!name[i].toLowerCase().matches("[а-я]+")){
                throw new MyException("Введены недопустимые символы при вводе ФИО");
            }
        }
        String[] date = array[1].split("\\.");
        try {
            System.out.println(date.length);
            if (Integer.parseInt(date[0]) < 1 ||
                    Integer.parseInt(date[0]) > 31 ||
                    Integer.parseInt(date[1]) < 1 ||
                    Integer.parseInt(date[1]) > 12 ||
                    Integer.parseInt(date[2]) < 1925 ||
                    Integer.parseInt(date[2]) > 2024){
                throw new MyException("Введена некорректная дата");
            }

        } catch (NumberFormatException e){
            throw new MyException("Введены некорректные символы при вводе даты рождения");
        }
        System.out.println("ok");
        if(!array[2].matches("[0-9]+")){
            throw new MyException("Введены некорректные символы при вводе номера телефона");
        }
        if(!(array[3].toLowerCase().contains("f") || array[3].toLowerCase().contains("m"))){
            throw new MyException("Введены некорректные символы при вводе пола");
        }

        String fileName = name[0] + ".txt";
        File file = new File(fileName);
        try(FileWriter fileWriter = new FileWriter(file, true)){
            for (int i = 0; i < name.length; i++) {
                fileWriter.write("<" + name[i] + ">");
            }
            fileWriter.write("<" + array[1] + ">");
            fileWriter.write("<" + array[2] + ">");
            fileWriter.write("<" + array[3] + ">\n");
        } catch (IOException e) {
                System.out.println("IOException " + e);
        }
    }
}


