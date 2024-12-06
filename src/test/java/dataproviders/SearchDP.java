package dataproviders;

import org.testng.annotations.DataProvider;

public class SearchDP {
    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
//                searched text - product
                {"placute frana"}
        };
    }
}
