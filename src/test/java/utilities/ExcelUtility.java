package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int row_count = ws.getLastRowNum();
		wb.close();
		fi.close();
		return row_count;
	}

	public int getCellCount(String sheetName, int row_num) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(row_num);
		int cell_count = row.getLastCellNum();
		wb.close();
		fi.close();
		return cell_count;
	}

	public String getCellData(String sheetName, int row_num, int col_num) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(row_num);
		cell = row.getCell(col_num);

		String data;
		try {
			// data=cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of
													// the cell type
		} catch (Exception e) {
			e.printStackTrace();
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int row_num, int col_num, String data) throws IOException {
		File xlfile = new File(path);
		if (!xlfile.exists()) // If file does't exist , create a new file
		{
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if(wb.getSheetIndex(sheetName)==-1) //If sheet doesn't exsit, create a new sheet
			wb.createSheet(sheetName);		
		ws = wb.getSheet(sheetName);
		if(ws.getRow(row_num)==null)  //If row doesn't exist, create a new row
			ws.createRow(row_num);
		row = ws.getRow(row_num);

		cell = row.createCell(col_num);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int row_num, int col_num) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(row_num);
		cell = row.getCell(col_num);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int row_num, int col_num) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(row_num);
		cell = row.getCell(col_num);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
