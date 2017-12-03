package br.edu.uepb.roda_a_roda.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Douglas Rafael
 */
public final class MyDateTime {

    private static DateFormat dateFormat;

    /**
     * Get/ Retrieve calendar instance.
     *
     * @return The Calendar.
     */
    public static Calendar getCalendar() {
        return GregorianCalendar.getInstance();
    }

    /**
     * Retorna a datetime atual no formato da string passado como parâmetro
     *
     * @param format_date O formato do datetime.
     * @return O datetime.
     */
    public static String getDateTime(String format_date) {
        Calendar calendar = GregorianCalendar.getInstance();

        dateFormat = new SimpleDateFormat(format_date);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Retorna data atual no formato do banco de dados yyyy-MM-dd HH:mm:ss
     *
     * @return Datetime no formato do banco de dados
     */
    public static String getDateTime() {
        return getDateTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Converte uma string datatime no formato passado como parâmetro e retorna
     * um objeto Date
     *
     * @param date_time A string datetime.
     * @param format_date A string do formato a converter
     * @return O objeto Date
     * @throws ParseException If an error occurs in converting string to date.
     */
    public static Date formatDateTime(String date_time, String format_date) throws ParseException {
        return new SimpleDateFormat(format_date).parse(date_time);
    }

    /**
     * Converte datatime no formato passado como parâmetro
     *
     * @param year O ano
     * @param month O mês
     * @param day O dia
     * @param hourOfDay A hora
     * @param minute O minuto
     * @param format_date Formato da tada a ser retornado
     * @return A data formatada
     */
    public static String toStringDateTime(int year, int month, int day, int hourOfDay, int minute, int second, String format_date) {
        Calendar calendar = GregorianCalendar.getInstance();
        // Value to be used for MONTH field. 0 is January
        calendar.set(year, month - 1, day, hourOfDay, minute, second);

        dateFormat = new SimpleDateFormat(format_date);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Converte data, time ou datatime no formato passado como parâmetro
     *
     * yyyy-MM-dd hh:mm:ss
     *
     * @param date_input
     * @param format_date
     * @param date
     * @param time
     * @return
     */
    public static String toStringDateTime(String date_input, String format_date, boolean date, boolean time) {
        String result = "";

        if (date && !time) {
            result = toStringDateTime(Integer.parseInt(date_input.substring(0, 4)), Integer.parseInt(date_input.substring(5, 7)),
                    Integer.parseInt(date_input.substring(8, 10)), 0, 0, 0, format_date);
        } else if (time && !date) {
            result = toStringDateTime(0, 0, 0, Integer.parseInt(date_input.substring(11, 13)), Integer.parseInt(date_input.substring(14, 16)), Integer.parseInt(date_input.substring(17, 19)), format_date);
        } else {
            result = toStringDateTime(Integer.parseInt(date_input.substring(0, 4)), Integer.parseInt(date_input.substring(5, 7)),
                    Integer.parseInt(date_input.substring(8, 10)), Integer.parseInt(date_input.substring(11, 13)), Integer.parseInt(date_input.substring(14, 16)), Integer.parseInt(date_input.substring(17, 19)), format_date);
        }

        return result;
    }

    /**
     * Converte datatime no formato passado como parâmetro
     *
     * yyyy-MM-dd hh:mm:ss
     *
     * @param date_input
     * @param format_date
     * @return
     */
    public static String toStringDateTime(String date_input, String format_date) {
        return toStringDateTime(Integer.parseInt(date_input.substring(0, 4)), Integer.parseInt(date_input.substring(5, 7)),
                Integer.parseInt(date_input.substring(8, 10)), Integer.parseInt(date_input.substring(11, 13)), Integer.parseInt(date_input.substring(14, 16)), Integer.parseInt(date_input.substring(17, 19)), format_date);
    }
}
