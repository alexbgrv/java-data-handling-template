package com.epam.izh.rd.online.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;


public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDate.format(date);
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(string, date);
    }

    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        long LeapYear = LocalDate.now().getYear() + 1;
        while(!Year.isLeap(LeapYear)){
            LeapYear++;
        }
        return LeapYear;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        return 366*60*60*24;
    }


}
