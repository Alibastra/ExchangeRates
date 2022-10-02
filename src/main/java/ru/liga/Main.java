package ru.liga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Rate> resultList = new ArrayList<>();
        String change = "";
        String fileName = "";
        Scanner in;
        int countDays = 0;
        System.out.println("Рады приветсвовать вас в нашем чудо сервисе по расчету курса валют!");
        while (!change.equals("q")) {
            System.out.println("Выберете одну из предоженных:");
            System.out.println("\t 1 - Доллар США" +
                    "\n\t 2 - Турецкая лира" +
                    "\n\t 3 - Евро" +
                    "\nДля выхода q..." );
            in = new Scanner(System.in);
            change = in.next();

            if (change.equals("q"))
                break;
            if(change.equals("1") || change.equals("2") || change.equals("3"))
            {

                switch (change){
                    case "1" : fileName = "Dollar"; break;
                    case "2" : fileName = "Lira"; break;
                    case "3" : fileName = "Euro"; break;
                }
                System.out.println(" 1 - Курс валют на завтра" +
                        "\n 2 - Курс валют на 7 дней" +
                        "\n Для выхода q..." );
                in = new Scanner(System.in);
                change = in.next();

                if (change.equals("q"))
                    break;
                if(change.equals("1") || change.equals("2"))
                {
                    if (change.equals("1"))
                        countDays = 1;
                    if (change.equals("2"))
                        countDays = 7;
                    new RateService().ExchangeRateForecast(fileName, countDays);
                } else {
                    System.out.println("Ошибка выбора символа, попробуйте снова!");
                }
            } else {
                System.out.println("Ошибка выбора символа, попробуйте снова!");
            }
        }
    }
}

