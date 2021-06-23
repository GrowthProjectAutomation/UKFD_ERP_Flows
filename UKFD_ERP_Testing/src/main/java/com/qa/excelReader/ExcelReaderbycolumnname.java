package com.qa.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderbycolumnname{
	public String[] excelReader(String filename,int sheetin,String columname) throws IOException
	{
	FileInputStream file=new FileInputStream(filename);
	XSSFWorkbook book=new XSSFWorkbook(file);
	XSSFSheet sheet=book.getSheetAt(sheetin);
	XSSFRow row=sheet.getRow(0);
	XSSFCell cell=null;
	int rows=sheet.getPhysicalNumberOfRows();
	int colNum=-1;
	for(int i=0;i<row.getLastCellNum();i++)
	{
		//compnyname.equals(companyname)
		if(row.getCell(i).getStringCellValue().equals(columname))
		{
			colNum=i;
		}
	}
	String [] data=new String[rows-1];
	for(int j=1;j<rows;j++)
	{	
	row=sheet.getRow(j);//second row
	cell=row.getCell(colNum);//
	if(cell!=null)
	{
		switch (cell.getCellType()) {
		case STRING :
		{
			data[j-1]=cell.getStringCellValue();
			break;
			
		}
		case NUMERIC:
		{
			data[j-1]=(int) Double.parseDouble(String.valueOf(cell)) + "";
			break;
			
		}
		
		default :
		{
			data[j-1]=cell.getStringCellValue();
		}
		
		}
		
		
	}
	else
	{
		data[j-1]="";
	}
	}
	return data;
	
}
	
}
















