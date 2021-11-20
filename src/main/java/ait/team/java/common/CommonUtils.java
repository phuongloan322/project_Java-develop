package ait.team.java.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.lang.Nullable;

import com.opencsv.CSVWriter;

public class CommonUtils {

	public final static String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";

	/*
	 * #2 Convert the string to the name of the day of the week to Japanese
	 * tradeDate: format Date to String follow the date of Japan dayOfWeek: is name
	 * of the day in Japanese
	 * 
	 * @author: diennv
	 */
	public static String formatDateString(String strDate) throws ParseException {
		Date tradeDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.JAPAN).parse(strDate);
		String dayOfWeek = new SimpleDateFormat("E", Locale.JAPAN).format(tradeDate);
		return dayOfWeek;
	}

	/*
	 * get yesterday
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	/*
	 * #4 Method check if the input string is a container code
	 * 
	 * @author: diennv
	 */
	public static boolean checkContainerCode(String containerCode) {
		// Check input null
		if (containerCode == null) {
			return false;
		}

		String ident = containerCode.substring(3, 4);
		String serial = containerCode.substring(4, 10);
		int checkDegit = Integer.parseInt(containerCode.substring(10));

		// get word from value ascii in List String and assign numeric value to word
		HashMap<String, Integer> keyValueHashMap = new HashMap<>();
		int valueHashMap = 10;
		for (int key = 0; key < 26; key++) {
			if (valueHashMap != 11 && valueHashMap != 22 && valueHashMap != 33) {
				keyValueHashMap.put(String.valueOf((char) (key + 65)), valueHashMap);
			} else {
				key--;
			}
		}

		// check Category identifier
		if (!(ident.equals("U") || ident.equals("Z") || ident.equals("J"))) {
			return false;
		}

		// check owner code, identifier is capitalize word and serial is number
		if (!(containerCode.substring(0, 4).matches("[A-Z]*$") && serial.matches("[0-9]*$"))) {
			return false;
		}

		// originalTotal to serial
		int originalTotal = 0;
		String[] containerArr = containerCode.split("");
		for (int i = 0; i < containerArr.length - 1; i++) {
			if (keyValueHashMap.containsKey(containerArr[i])) {
				originalTotal += keyValueHashMap.get(containerArr[i]) * Math.pow(2, i);
			} else {
				originalTotal += Integer.parseInt(containerArr[i]) * Math.pow(2, i);
			}
		}
		int finalTotal = originalTotal - Math.round(originalTotal / 11) * 11;

		// Check Degit
		if (finalTotal != checkDegit) {
			return false;
		}

		return true;
	}

	/*
	 * #9 Use reflection to shallow copy simple type fields with matching names from
	 * one object to another
	 * 
	 * @param fromObj the object to copy from
	 * 
	 * @param toObj the object to copy to
	 * 
	 * @author: diennv
	 */
	public static void copyMatchingFields(Object fromObj, Object toObj)
			throws IllegalArgumentException, IllegalAccessException {

		if (fromObj == null || toObj == null)
			throw new NullPointerException("Source and destination objects must be non-null");

		@SuppressWarnings("rawtypes")
		Class fromClass = fromObj.getClass();
		@SuppressWarnings("rawtypes")
		Class toClass = toObj.getClass();

		Field[] fromObjFields = fromClass.getDeclaredFields();
		Field[] toObjFields = toClass.getDeclaredFields();
		for (Field frObj : fromObjFields)
			for (Field tObj : toObjFields) {
				if (tObj.getName() == frObj.getName() && tObj.getType() == frObj.getType()) {
					if (tObj.getType() == String.class || tObj.getType() == int.class || tObj.getType() == Integer.class
							|| tObj.getType() == char.class || tObj.getType() == Character.class) {
						tObj.setAccessible(true);
						tObj.setAccessible(true);
						tObj.set(toObj, frObj.get(fromObj));
					} else if (tObj.getType() == Date.class) {
						Date date = (Date) frObj.get(fromObj);
						tObj.setAccessible(true);
						tObj.setAccessible(true);
						tObj.set(toObj, date != null ? date.clone() : null);
					}
				}
			}

	}

	/*
	 * Read file csv INPUT: path file csv OUTPUT: csv file data as
	 * List<List<String>>
	 */
	public static List<List<String>> readCSV(String file) throws FileNotFoundException, IOException {
		String csvFile = file;

		// contains return data
		List<List<String>> result = new ArrayList<>();
		try (Reader reader = new FileReader(csvFile); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				List<String> data = new ArrayList<>();
				for (String csvRe : csvRecord) {
					data.add(csvRe);
				}
				result.add(data);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/*
	 * write csv file input: header line, data line by line, file name, directory
	 * path to save output: true if successfull else unsuccessful
	 */
	public static boolean writeCSV(String[] header, List<String[]> data, String file, String folder)
			throws IOException {
		String extensionFile = file.substring(file.lastIndexOf("."), file.length());
		if (!extensionFile.toLowerCase().equals(".csv")) {
			return false;
		}
		;
		// creat folder if not exists.
		File fileSaveDir = new File(folder);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String csvFile = folder + "/" + file;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
			@SuppressWarnings("resource")
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header));
			// save data to csv file
			for (String[] data_line : data) {
				csvPrinter.printRecord(data_line);
			}
			csvPrinter.flush();
			// return true if add successful
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// return false if unsuccessful
		return false;
	}

	// Convert String to MD5
	/*
	 * INPUT: string to convert to md5 OUTPUT: string has been converted to md5
	 */
	public String stringToMD5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		// String to byte hashed md5
		byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
		StringBuilder sb = new StringBuilder();
		// byte to String
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	/*
	 * day of week in month INPUT: date OUTPUT: day of week in month 2 dimensional
	 * array
	 */
	public String[][] toDayOfWeekInMonth(Date date) throws ParseException {
		// Date to Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// result contains return data
		String[][] result = new String[7][7];

		// save day of week Japanese at the first line of array
		String[] header = { "日", "月", "火", "水", "木", "金", "土" };
		result[0] = header;

		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		// first day next month
		String firstDayNextMonth = "1/" + (calendar.get(Calendar.MONTH) + 2) + "/" + calendar.get(Calendar.YEAR);
		Date dateNextMonth = new SimpleDateFormat("dd/MM/yyyy").parse(firstDayNextMonth);

		// last day month
		long maxDayOfMonth = (dateNextMonth.getTime() - date.getTime()) / 86400000 + Long.valueOf(dayOfMonth) - 1;

		// find down calendar
		int line_array = 0;
		if (dayOfMonth / dayOfWeekInMonth > dayOfWeek) {
			line_array = dayOfWeekInMonth + 1;
		} else {
			line_array = dayOfWeekInMonth;
		}
		int cell_array = dayOfWeek - 1;
		int checkDayOfMonth = dayOfMonth;

		// browser to the first date of month
		while (checkDayOfMonth > 0) {
			result[line_array][cell_array] = checkDayOfMonth + "";
			cell_array--;
			if (cell_array == -1) {
				line_array--;
				cell_array = 6;
			}
			checkDayOfMonth--;
		}

		// find up calendar
		if (dayOfMonth / dayOfWeekInMonth > dayOfWeek) {
			line_array = dayOfWeekInMonth + 1;
		} else {
			line_array = dayOfWeekInMonth;
		}
		cell_array = dayOfWeek - 1;
		checkDayOfMonth = dayOfMonth;

		// browser to the last date of month
		while (checkDayOfMonth <= maxDayOfMonth) {
			result[line_array][cell_array] = checkDayOfMonth + "";
			cell_array++;
			if (cell_array == 7) {
				line_array++;
				cell_array = 0;
			}
			checkDayOfMonth++;
		}
		return result;
	}

	// Code by: Nhatpc
	public static String convertDecimal(BigDecimal decimal, int decimalPart) {
		if (decimal.compareTo(new BigDecimal(0)) >= 0) {
			return decimal.setScale(decimalPart, BigDecimal.ROUND_UP).toString();
		} else {
			return decimal.setScale(decimalPart, BigDecimal.ROUND_DOWN).toString();
		}
	}

	// Code by: Nhatpc
	public static long monthsBetweenTwoDates(LocalDate fromDate, LocalDate toDate) {
		if (fromDate != null && toDate != null) {
			long monthsBetween = ChronoUnit.MONTHS.between(fromDate.withDayOfMonth(1), toDate.withDayOfMonth(1));
			return monthsBetween;
		}
		return 0;
	}

	// Code by: Nhatpc
	public static long monthsBetweenTwoDates(Date fromDate, Date toDate) {
		if (fromDate != null && toDate != null) {

			Instant instantFromDate = Instant.ofEpochMilli(fromDate.getTime());
			LocalDate localFromDate = LocalDateTime.ofInstant(instantFromDate, ZoneId.systemDefault()).toLocalDate();

			Instant instantToDate = Instant.ofEpochMilli(toDate.getTime());
			LocalDate localToDate = LocalDateTime.ofInstant(instantToDate, ZoneId.systemDefault()).toLocalDate();

			long monthsBetween = ChronoUnit.MONTHS.between(localFromDate.withDayOfMonth(1),
					localToDate.withDayOfMonth(1));
			return monthsBetween;
		}
		return 0;
	}

	public static String dateToString(Date date, String format) {
		String dateFormatedStr = "";
		if (date == null || isEmpty(format)) {
			return dateFormatedStr;
		}

		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			dateFormatedStr = dateFormat.format(date);
			return dateFormatedStr;
		} catch (Exception e) {
			e.printStackTrace();
			return dateFormatedStr;
		}
	}

	public static boolean isEmpty(@Nullable Object str) {
		return (str == null || "".equals(str));
	} 

	public static void writeDataToCsvUsingStringArray(PrintWriter writer, List<String[]> contentCsv) {

		try (CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			for (String[] contentLine : contentCsv) {
				csvWriter.writeNext(contentLine);

			}

			System.out.println("Write CSV using CSVWriter successfully!");
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}	
}
	/*
	 * write data form List<String[]> to CSV
	 * input: PrintWrite, data: List<String[]> 
	 */
	public static void writeDataToCsv(PrintWriter writer,List<String[]> contentCsvList) {
		
		try {
			@SuppressWarnings("resource")
			CSVWriter csvWriter = new CSVWriter(writer,
	                CSVWriter.DEFAULT_SEPARATOR,
	                CSVWriter.NO_QUOTE_CHARACTER,
	                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                CSVWriter.DEFAULT_LINE_END);
			//add content CSV file
			for (String[] contentLine : contentCsvList) {
				csvWriter.writeNext(contentLine);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Date> getDateOfWeek(Date givenDate) {

		List<Date> dateOfWeekList = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		Calendar firstDate = Calendar.getInstance();
		Calendar lastDate = Calendar.getInstance();
		cal.setTime(givenDate);
		firstDate.setTime(givenDate);
		lastDate.setTime(givenDate);

		firstDate.set(Calendar.DAY_OF_MONTH, firstDate.getActualMinimum(Calendar.DAY_OF_MONTH) - 1);
		lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);

		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {

			cal.set(Calendar.DAY_OF_WEEK, i);
			if (cal.getTime().after(firstDate.getTime()) && cal.getTime().before(lastDate.getTime())) {
				dateOfWeekList.add(cal.getTime());
			}
		}
		return dateOfWeekList;
	}
	//check format date
	public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }}
