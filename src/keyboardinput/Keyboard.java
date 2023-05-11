//********************************************************************
//  Keyboard.java       Author: Lewis and Loftus
//
//  Facilitates keyboard input by abstracting details about input
//  parsing, conversions, and exception handling.
//********************************************************************

package keyboardinput;

import java.io.*;
import java.util.*;

/**
 * Keyboard.java
 * <p>
 * Classe di Utility che semplifica le operazioni di input da tastiera
 *
 * @author Dott.ssa Appice A.
 */

public class Keyboard {

	/**
	 * Se true, stampa gli errori di input
	 */
	private static boolean printErrors = true;

	/**
	 * Contatore degli errori di input
	 */
	private static int errorCount = 0;

	/**
	 * Restituisce il numero di errori di input
	 * @return il numero di errori di input
	 */
	public static int getErrorCount() {
		return errorCount;
	}

	/**
	 * Resetta il contatore degli errori di input
	 * @param count un intero che non viene utilizzato
	 */
	public static void resetErrorCount(int count) {
		errorCount = 0;
	}

	/**
	 * Restituisce un valore booleano che indica se gli errori di input sono stampati su System.out
	 * @return true se gli errori di input vengono stampati, false altrimenti
	 */
	public static boolean getPrintErrors() {
		return printErrors;
	}

	/**
	 * Imposta il valore booleano che indica se gli errori di input sono stampati su System.out
	 * @param flag il valore booleano da impostare a printErrors
	 */
	public static void setPrintErrors(boolean flag) {
		printErrors = flag;
	}

	/**
	 * Gestisce un errore di input aumentando errorCount e stampando il messaggio di errore qualora printErrors fosse true
	 * @param str il messaggio di errore da stampare
	 */
	private static void error(String str) {
		errorCount++;
		if (printErrors)
			System.out.println(str);
	}

	/**
	 * Rappresenta il token attuale
	 */
	private static String current_token = null;

	/**
	 * Oggetto StringTokenizer che aiuta a leggere i token
	 */
	private static StringTokenizer reader;

	/**
	 * BufferedReader utilizzato per la lettura in input
	 */
	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	/**
	 * Metodo per leggere il prossimo token
	 * @return il valore del token letto
	 */
	private static String getNextToken() {
		return getNextToken(true);
	}

	/**
	 * Legge il token successivo
	 * @param skip se true, salta i delimitatori
	 * @return il valore del token letto
	 */
	private static String getNextToken(boolean skip) {
		String token;

		if (current_token == null)
			token = getNextInputToken(skip);
		else {
			token = current_token;
			current_token = null;
		}

		return token;
	}

	/**
	 * Legge il prossimo token
	 * @param skip se true, salta i delimitatori
	 * @return il valore del token letto
	 */
	private static String getNextInputToken(boolean skip) {
		final String delimiters = " \t\n\r\f";
		String token = null;

		try {
			if (reader == null)
				reader = new StringTokenizer(in.readLine(), delimiters, true);

			while (token == null || ((delimiters.indexOf(token) >= 0) && skip)) {
				while (!reader.hasMoreTokens())
					reader = new StringTokenizer(in.readLine(), delimiters,
							true);

				token = reader.nextToken();
			}
		} catch (Exception exception) {
			token = null;
		}

		return token;
	}

	/**
	 * Metodo per verificare se la linea corrente è terminata
	 * @return true se la linea è terminata, false altrimenti
	 */
	public static boolean endOfLine() {
		return !reader.hasMoreTokens();
	}

	/**
	 * Metodo per leggere una stringa letta dall'input standard
	 * @return la stringa letta
	 */
	public static String readString() {
		String str;

		try {
			str = getNextToken(false);
			while (!endOfLine()) {
				str = str + getNextToken(false);
			}
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			str = null;
		}
		return str;
	}

	/**
	 * Legge una parola da input. Per parola si intende una stringa delimitata da uno spazio prima e dopo
	 * @return la parola letta
	 */
	public static String readWord() {
		String token;
		try {
			token = getNextToken();
		} catch (Exception exception) {
			error("Error reading String data, null value returned.");
			token = null;
		}
		return token;
	}

	/**
	 * Legge un valore booleano dall'input
	 * @return il valore booleano letto
	 */
	public static boolean readBoolean() {
		String token = getNextToken();
		boolean bool;
		try {
			if (token.toLowerCase().equals("true"))
				bool = true;
			else if (token.toLowerCase().equals("false"))
				bool = false;
			else {
				error("Error reading boolean data, false value returned.");
				bool = false;
			}
		} catch (Exception exception) {
			error("Error reading boolean data, false value returned.");
			bool = false;
		}
		return bool;
	}

	/**
	 * Legge un carattere dall'input
	 * @return il carattere letto
	 */
	public static char readChar() {
		String token = getNextToken(false);
		char value;
		try {
			if (token.length() > 1) {
				current_token = token.substring(1, token.length());
			} else
				current_token = null;
			value = token.charAt(0);
		} catch (Exception exception) {
			error("Error reading char data, MIN_VALUE value returned.");
			value = Character.MIN_VALUE;
		}

		return value;
	}

	/**
	 * Legge un valore intero dall'input
	 * @return il valore intero letto
	 */
	public static int readInt() {
		String token = getNextToken();
		int value;
		try {
			value = Integer.parseInt(token);
		} catch (Exception exception) {
			error("Error reading int data, MIN_VALUE value returned.");
			value = Integer.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge un valore long dall'input
	 * @return il valore long letto
	 */
	public static long readLong() {
		String token = getNextToken();
		long value;
		try {
			value = Long.parseLong(token);
		} catch (Exception exception) {
			error("Error reading long data, MIN_VALUE value returned.");
			value = Long.MIN_VALUE;
		}
		return value;
	}

	/**
	 * Legge un valore float dall'input
	 * @return il valore float letto
	 */
	public static float readFloat() {
		String token = getNextToken();
		float value;
		try {
			value = (Float.parseFloat(token));
		} catch (Exception exception) {
			error("Error reading float data, NaN value returned.");
			value = Float.NaN;
		}
		return value;
	}

	/**
	 * Legge un valore double dall'input
	 * @return il valore double letto
	 */
	public static double readDouble() {
		String token = getNextToken();
		double value;
		try {
			value = (Float.parseFloat(token));
		} catch (Exception exception) {
			error("Error reading double data, NaN value returned.");
			value = Double.NaN;
		}
		return value;
	}
}
