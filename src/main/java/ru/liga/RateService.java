package ru.liga;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RateService {
     /**
     * Чтение курса валют из csv-файла
     * @param fileName имя файла
     * @return списов валют (объектов Rate), полученных из csv-файла
     */
    //сделать чтение из ресурсов
    public List<Rate> readFromCsvFail(String fileName) throws IOException {
        List<Rate> resultList = new ArrayList<>();
        String row;

        fileName = fileName+".csv";
        /*File csvFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
        BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));*/
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(in));
        while ((row = csvReader.readLine())!= null) {
            String[] tokensCsv = row.split(";");
            if (!tokensCsv[0].equals("nominal")) {
                Rate rate = new Rate(Integer.parseInt(tokensCsv[0])
                                    , LocalDate.parse(tokensCsv[1], DateTimeFormatter.ofPattern("d.MM.yyyy"))
                                    , Double.parseDouble(tokensCsv[2].replace(',','.'))
                                    , tokensCsv[3]);
                resultList.add(rate);
            }
        }
        resultList = resultList.stream()
                .sorted((o1,o2)-> o1.getDayDate().compareTo(o2.getDayDate()))
                .collect(Collectors.toList());
        return resultList;
    }

    private List<Rate> ExchangeRate(List<Rate> historyRate, int countDays){
        LocalDate nowDate = LocalDate.now();
        LocalDate currentDate = nowDate.plusDays(countDays);
        int cntAddRate, j;
        for (cntAddRate = 1; cntAddRate<=countDays;cntAddRate++){
            double curs = 0;
            int currentRate = historyRate.size() -7;
            while (currentRate<historyRate.size()) {
                curs += historyRate.get(currentRate).getCurs();
                currentRate++;
            }
            curs /= 7;

            historyRate.add(new Rate(historyRate.get(1).getNominal()
                    , nowDate.plusDays(cntAddRate)
                    , curs
                    , historyRate.get(1).getNameRate()));
        }

        return historyRate;
    }

    /**
     * Прогнозирование курса валют на указанный период
     * @param fileName файл со списком валют, известных на текущий момент
     * @param countDays кол-во дней для прогноза
     * @return прогноз валюты на неделю
     */
    public void ExchangeRateForecast(String fileName, int countDays) throws IOException {
        List<Rate> historyRate = readFromCsvFail(fileName);
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        historyRate = ExchangeRate(historyRate,countDays);

        Locale localeRu = new Locale("ru", "RU");

        if (countDays == 1){
            System.out.println("Курс " + historyRate.get(1).getNameRate()+" на завтра");
        }else {
            System.out.println("Курс " + historyRate.get(1).getNameRate() +" на неделю");
        }
        for (int currentRate = historyRate.size()- countDays; currentRate < historyRate.size(); currentRate++ )
        {

            System.out.println("\t"+historyRate.get(currentRate).getDayDate().getDayOfWeek().getDisplayName(TextStyle.SHORT,localeRu)
                                +" "+historyRate.get(currentRate).getDayDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")))
                                +" - "+decimalFormat.format(historyRate.get(currentRate).getCurs()));
        }
        System.out.println("");
    }

}
