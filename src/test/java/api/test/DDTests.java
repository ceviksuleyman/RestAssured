package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstname, String lastname, String userEmail, String pwd, String ph) {

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {

        Response response = UserEndPoints.deleteUser(userName);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
