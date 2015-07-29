/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Wallace
 */
public class Utils {

    public static String[] getValoresMes() {
        String[] listaValores = new String[31];
        int count = 0;
        for (String listaValore1 : listaValores) {
            listaValores[count] = "" + (1 + count);
            count++;
        }
        return listaValores;
    }

    public static String[] getValoresAnos() {
        String[] listaValores = new String[51];
        int count = 0;
        for (String listaValore1 : listaValores) {
            listaValores[count] = "" + (2000 - count);
            count++;
        }
        return listaValores;
    }

    public static Date geraData(int indexMes, int indexDia, int indexAno) {
        indexDia = (indexDia + 1);
        indexMes = (indexMes);
        indexAno = (2000 - indexAno);
        Calendar calendar = Calendar.getInstance();
        calendar.set(indexAno, indexMes, indexDia, 0, 0, 0);
        return calendar.getTime();
    }

    public static String maskUserType(Integer type) {
        return (type == 0) ? "Admisnitrador" : "Funcionário";
    }
}
