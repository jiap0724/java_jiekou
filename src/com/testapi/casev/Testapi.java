/*
 * 
 * 
 */
package com.testapi.casev;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testapi.until.ExcelUtils;
import com.testapi.until.Getcode;
import com.testapi.until.Makejson;
import com.testapi.until.ParseJson;

public class Testapi {
	@DataProvider(name = "DP1")
    public Object[][] createData() throws Exception {
		ExcelUtils excelUtils=new ExcelUtils();
		String path=System.getProperty("user.dir")+File.separator+"casedata"+File.separator+"casedata.xlsx";
    	Object[][] m = ExcelUtils.getTableArray(path,"Sheet1");
		return m;
    }
  @Test(dataProvider="DP1")
  public void f(String url,String path,String meth,String map,String qiwang) {
	String param1 = Makejson.makejson(map);
	Getcode getcode=new Getcode();
		url = url + path;
	String resulst=getcode.getHttpResponse(param1, url, meth);
		String baowen = ParseJson.Json(resulst);
		assertEquals(baowen, qiwang);
  }

	@BeforeTest
  public void beforeTest() {
  }

	@AfterTest
  public void afterTest() {
  }
}