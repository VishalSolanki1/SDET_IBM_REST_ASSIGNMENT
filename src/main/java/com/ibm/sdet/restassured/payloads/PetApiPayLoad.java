package com.ibm.sdet.restassured.payloads;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.sdet.restassured.pojos.Category;
import com.ibm.sdet.restassured.pojos.PetPojo;
import com.ibm.sdet.restassured.pojos.Tags;

import io.restassured.path.json.JsonPath;


public class PetApiPayLoad {
		
	public static Long getPetId(String response) {
		Long petId;
		JsonPath jsonPath = new JsonPath(response);
		petId = (long)jsonPath.get("id");
	
		System.out.println("PetId: "+petId);
		return petId;
	}

 
	public PetPojo petApiPayLoad(String categoryName, int categoryId, String tagsName, int tagsId, 
			String photoUrlName, String name, String status) {
		
    	
		PetPojo petPojo = new PetPojo();
		
		Category category = new Category();
		category.setId(categoryId);
		category.setName(categoryName);
		
		Tags tags = new Tags();
		tags.setId(tagsId);
		tags.setName(tagsName);
		
		List<Tags> tagList = new ArrayList<>();
		tagList.add(tags);
		
		List<String> photoUrls = new ArrayList<>();
		photoUrls.add(photoUrlName);
		
		petPojo.setName(name);
		petPojo.setCategory(category);
		petPojo.setTags(tagList);
		petPojo.setStatus(status);
		
		return petPojo;
	}
	
	  public PetPojo getPetApiPayLoad(String categoryName, String category, String tagsName, String tag, String photoUrlName, String name,
			    String status){ 
		  
		  int categoryID = Integer. parseInt(category);
		  int tagsID = Integer. parseInt(tag);
		  
		  PetPojo petPojo = petApiPayLoad(categoryName, categoryID, tagsName, tagsID, photoUrlName, name, status); 
	  return petPojo;
	 }
	

	
	@DataProvider(name="PetsAPIPayLoad")
    public Object[][] readDataFromExcel() throws IOException {
	
	    File srcFile = new File("C:\\Users\\VishalSolanki\\Desktop\\SDET\\restassign\\PetAPIData.xlsx");
	    FileInputStream fileinputstream = new FileInputStream(srcFile);
		
	    XSSFWorkbook workbook = new XSSFWorkbook(fileinputstream);
		
		int firstSheet = workbook.getFirstVisibleTab();
		
		//ystem.out.println("First Tab index is " + firstSheet);
		
	    XSSFSheet sheet = workbook.getSheetAt(firstSheet);
	    
		int rowCount = sheet.getLastRowNum();
		//System.out.println(rowCount);
		int cellCount= sheet.getRow(0).getLastCellNum();
		//System.out.println(cellCount);
		Object[][] parameters= new Object[rowCount][cellCount];
		
		   for(int i=1; i<=rowCount; i++){
				XSSFRow row = sheet.getRow(i);
				  for(int j=0; j<cellCount; j++){
				  XSSFCell cell = row.getCell(j);
					DataFormatter df = new DataFormatter();
					String data = df.formatCellValue(cell);
					System.out.println(data);
					parameters[i-1][j]=data;
					
		           }
			}
		   return parameters;
}
	
}
