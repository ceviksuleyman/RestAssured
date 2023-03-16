package api.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");
        int columnCount = xl.getCellCount("Sheet1", 1);

        String[][] apiData = new String[rowNum][columnCount];

        for (int i = 1; i <= rowNum; i++) {

            for (int j = 0; j < columnCount; j++) {

                apiData[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "UserNames")
    public static Object[][] getUserNames() throws IOException {

        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");

        String[][] apiData = new String[rowNum][1];


        for (int i = 1; i <= rowNum; i++) {

            apiData[i - 1][0] = xl.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }

    public static void main(String[] args) throws IOException {
        Object[] arr = getUserNames();
        System.out.println(Arrays.deepToString(arr));
    }
}
