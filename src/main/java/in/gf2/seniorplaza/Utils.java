package in.gf2.seniorplaza;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

import in.gf2.seniorplaza.domain.exception.InvalidDateException;

public class Utils {

	public static boolean isCPFValido(String cpf) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
	}
	
	public static Timestamp clearTime(Timestamp timestamp) {
		return new Timestamp(clearTime(timestamp.getTime()));
	}

	public static long clearTime(long timestamp) {
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTimeInMillis(timestamp);
		clearTime(calendar);

		return calendar.getTime().getTime();
	}

	public static void clearTime(Calendar calendar) {
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		calendar.clear(Calendar.AM_PM);
	}
	
	public static Timestamp dataAdd(Timestamp data, int amount, int field) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(data.getTime());
		cal.add(field, amount);

		return new Timestamp(cal.getTimeInMillis());
	}

	public static Timestamp encodeDate(int ano, int mes, int dia) {
		Calendar c = new GregorianCalendar();
		c.set(ano, mes-1, dia);
		c.clear(Calendar.HOUR_OF_DAY);
		c.clear(Calendar.HOUR);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		c.clear(Calendar.AM_PM);

		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp encodeTime(Timestamp data, int hour, int minute, int second) {
		Calendar c = new GregorianCalendar();
		c.setTime(data);
		c.clear(Calendar.HOUR_OF_DAY);
		c.clear(Calendar.HOUR);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		c.clear(Calendar.AM_PM);
		c.add(Calendar.HOUR, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);

		return new Timestamp(c.getTimeInMillis());
	}
	
	public static int getDayOfWeek(Timestamp date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date.getTime());
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static Timestamp timestampFromString(String date) throws InvalidDateException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			throw new InvalidDateException(e.getLocalizedMessage());
		}
		return new Timestamp(dt.getTime());
	}
}
