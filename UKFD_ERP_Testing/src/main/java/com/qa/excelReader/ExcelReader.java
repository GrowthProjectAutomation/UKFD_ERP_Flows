package com.qa.excelReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public Object[][] readExcelData(String Filepath,int sheet1) throws IOException
	{
		File file=new File(Filepath);
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook book=new XSSFWorkbook(fis);
		XSSFSheet sheet=book.getSheetAt(sheet1);
        int colNum = sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[sheet.getLastRowNum()][colNum]; 
		System.out.println("columns are" +colNum);
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(i).getLastCellNum();j++)
			{
				Cell cell=sheet.getRow(i).getCell(j);
				if(cell!= null) {
					switch(cell.getCellType()) {

					
					case STRING :
					{
						data[i-1][j]=cell.getStringCellValue();
						break;
						
					}
					case NUMERIC:
					{
						data[i-1][j]=(int) Double.parseDouble(String.valueOf(cell)) + "";
						break;
						
					}
					
					default :
					{
						data[i-1][j]=cell.getStringCellValue();
					}
					}
					
				}
				else
				{
					data[i-1][j]="";
				}
				
		}
		}
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				System.out.println(data[i][j]);
			}
		}
		
		
		
		return data;
		
	}

}
