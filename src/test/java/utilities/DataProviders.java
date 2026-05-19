package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = ("C:\\Users\\User\\opencart_LoginData.xlsx");  //taking xl file from test data

        ExcelUtility xlutil = new ExcelUtility(path);  // creating object for XUtilities

        int totallrows = xlutil.getRowCount("sheet1");
        int totallcols = xlutil.getCellCount("sheet1", 1);

        Object logindata[][] = new String[totallrows][totallcols]; //creating 2dimensional array witch can store data

        for (int i = 1; i <= totallrows; i++)  //reading data from xl and storing in 2dim array
        {
            for (int j = 0; j < totallcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("sheet1", i, j);
            }
        }
        return logindata;
    }
}
