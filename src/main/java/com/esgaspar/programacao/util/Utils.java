package com.esgaspar.programacao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static SimpleDateFormat formatoData() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    public static String dateToString(LocalDate date) {
        return formatoData().format(DateUtils.asDate(date));
    }

    public static LocalDate StringToDate(String date) {
        try {
            return Utils.DateUtils.asLocalDate(formatoData().parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static SimpleDateFormat formatoDataUrl() {
        return new SimpleDateFormat("dd-MM-yyyy");
    }

    public static String dateToStringUrl(LocalDate date) {
        return formatoData().format(DateUtils.asDate(date));
    }

    public static LocalDate StringToDateUrl(String date) {
        try {
            return Utils.DateUtils.asLocalDate(formatoDataUrl().parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static class DateUtils {

        public static Date asDate(LocalDate localDate) {
            return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }

        public static Date asDate(LocalDateTime localDateTime) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        public static LocalDate asLocalDate(Date date) {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        }

        public static LocalDateTime asLocalDateTime(Date date) {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }
}
