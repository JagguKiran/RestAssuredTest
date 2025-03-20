package api.utilities;

import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.payload.User;

public class ExcelUtility {
	private String xlPath;
	private XSSFWorkbook workbook;
	private FileInputStream fis;
	private XSSFSheet sheetName; 
	private User user;
	public ExcelUtility(String path){
		this.xlPath=path;
	}
	public XSSFWorkbook getWorkbook() {
		try{
			fis=new FileInputStream(xlPath);
			workbook=new XSSFWorkbook(fis);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			if(fis!=null)fis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}
	public List<User> retriveUserDetails(){
		List<User> al=new ArrayList<User>();
		workbook=getWorkbook();
		if(workbook==null)return al;
		sheetName=workbook.getSheetAt(0);
		if(sheetName==null)return al;
		Iterator<Row> rowIterator = sheetName.rowIterator();
		rowIterator.next();
		while(rowIterator.hasNext()){
			Row rows= rowIterator.next();
			user=new User();
			Iterator<Cell> cells = rows.cellIterator();
			while(cells.hasNext()){
				Cell cell = cells.next();
				if(cell.getColumnIndex()==0)user.setId(Integer.parseInt(cell.getStringCellValue()));
				if(cell.getColumnIndex()==1)user.setUsername(cell.getStringCellValue());
				if(cell.getColumnIndex()==2)user.setFirstName(cell.getStringCellValue());
				if(cell.getColumnIndex()==3)user.setLastName(cell.getStringCellValue());
				if(cell.getColumnIndex()==4)user.setEmail(cell.getStringCellValue());
				if(cell.getColumnIndex()==5)user.setPassword(cell.getStringCellValue());
				if(cell.getColumnIndex()==6)user.setPhone(cell.getStringCellValue());
				if(cell.getColumnIndex()==7)user.setUserStatus(Integer.parseInt(cell.getStringCellValue()));
				
			}
			al.add(user);
		}
		al.remove(al.size()-1);
		return al;
	}
}
