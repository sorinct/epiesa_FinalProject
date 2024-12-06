package dataproviders;

import org.testng.annotations.DataProvider;

public class CartDP {
    @DataProvider
    public Object[][] cartDataProvider() {
        return new Object[][]{
//                (product + price)
                {"Scut motor ASAM 55260", 238}
        };
    }
}
