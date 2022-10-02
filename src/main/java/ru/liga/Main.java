package ru.liga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String header = "\nВыберете одну из предоженных валют:" +
                                        "\n\t 1 - Доллар США" +
                                        "\n\t 2 - Турецкая лира" +
                                        "\n\t 3 - Евро" +
                                        "\nДля выхода q...";
    public static final String line = " 1 - Курс валют на завтра" +
                                    "\n 2 - Курс валют на 7 дней" +
                                    "\n Для выхода q...";
    public static final String errMsg = "Ошибка выбора символа, попробуйте снова!";
    public static void main(String[] args) throws IOException {
        String change = "";
        Scanner in;
        int countDays = 0;
        while (!change.equals("q")) {
            System.out.println(header);
            in = new Scanner(System.in);
            change = in.next();
            if (change.equals("1")||change.equals("2")||change.equals("3")) {
                int changeFile = Integer.parseInt(change);
                System.out.println(line);
                in = new Scanner(System.in);
                change = in.next();

                if (change.equals("1")) {
                    countDays = 1;
                    new RateService().ExchangeRateForecast(changeFile, countDays);
                } else if (change.equals("2")) {
                    countDays = 7;
                    new RateService().ExchangeRateForecast(changeFile, countDays);
                } else if(!change.equals("q")){
                    System.out.println(errMsg);
                }
            } else if (!change.equals("q")){
                System.out.println(errMsg);
            }
        }
    }
}

