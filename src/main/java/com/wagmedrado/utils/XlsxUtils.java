package com.wagmedrado.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author w4gne
 */
public class XlsxUtils {
  public static void lerPlanilha() {
    String fileName = ".\\myXlsx.xlsx";
    try (FileInputStream fis = new FileInputStream(fileName)) {
      Workbook wb = new XSSFWorkbook(fis);
      //Workbook wb = WorkbookFactory.create(is);
      Sheet sheet = wb.getSheetAt(1);
      if (sheet != null) {
        Iterator<Row> iterator = sheet.rowIterator();
        DataFormatter dataFormatter = new DataFormatter();
        while(iterator.hasNext()) {
          Row row = iterator.next();
          // skip headers
          if (row.getRowNum() < 9) {
            continue;
          }
          Cell cell = row.getCell(10);
          String notaFiscal = dataFormatter.formatCellValue(cell);
          if (!notaFiscal.isEmpty()) {
            cell = row.getCell(11);
            String processo = dataFormatter.formatCellValue(cell);
            System.out.println(row.getRowNum() + "\t " + notaFiscal + "\t " + processo);
          }
        }
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(XlsxUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(XlsxUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void main(String[] args) {
    lerPlanilha();
  }
}
