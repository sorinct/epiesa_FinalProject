package dataproviders;

import models.AccountModel;
import org.testng.annotations.DataProvider;
import utils.DatabaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginDP {

    @DataProvider
    public Object[][] loginDataProvider() {
        return new Object[][]{
//                email adress + password
          {"blocnarrecords1@gmail.com", "Test123."}
//          {"blocnarrecords1@gmail.", "Test123."},
//          {"blocnarrecords1@gmailcom", "Test123."},
//          {"blocnarrecords1@.com", "Test123."},
//          {"blocnarrecords1gmail.com", "Test123."},
//          {"@gmail.com", "Test123."}
        };
    }


    @DataProvider(name = "loginSQLDataProvider")
    public Iterator<Object[]> loginSQLDataProvider() throws SQLException {
        Collection<Object[]> dp = new ArrayList<>();
        DatabaseUtils databaseUtils = new DatabaseUtils();

//     connect to DB and get data
        Connection connection = databaseUtils.connect();
        Statement statement = databaseUtils.getStatement(connection);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM epiesa_account");

        while (resultSet.next()) {
            AccountModel accountModel = new AccountModel(databaseUtils.getElementFromDB(resultSet, "email"),
                    databaseUtils.getElementFromDB(resultSet, "First Name"),
                    databaseUtils.getElementFromDB(resultSet, "Last Name"),
                    databaseUtils.getElementFromDB(resultSet, "password"));
            dp.add(new Object[]{accountModel});
        }
        return dp.iterator();
    }
}
