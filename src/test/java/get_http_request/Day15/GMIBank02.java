package get_http_request.Day15;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GMIBank02 extends GMIBankBaseUrl {
     /*
http://www.gmibank.com/api/tp-customers end point'ine
request gönderin
 1) Tüm Customer bilgilerini ekrana yazdırırn.

 2) Tüm Customer emaillerini ekrana yazdırın.

 3) Tüm Customer emaillerini text dosyası olarak kaydedin

 4) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
    E-maillerinin olduğunu doğrulayın
 */

    @Test
    public void test() throws IOException {
        Customer[] customers;
        // http://www.gmibank.com/api/tp-customers  spec yapisi olustur
        spec03.pathParam("parametre1","tp-customers");

        Response response = given().headers("Authorization", "Bearer " + generateToken())
                .when().spec(spec03).get("/{parametre1}")
                .then().contentType(ContentType.JSON)
                .extract().response();

        ObjectMapper obj=new ObjectMapper();
        customers=obj.readValue(response.asString(),Customer[].class);

        // 1) Tüm Customer bilgilerini ekrana yazdırırn.
        for (int i=0; i<customers.length;i ++)
            System.out.println("Customers Email : "+ customers[i].getEmail());


      //2) Tüm Customer emaillerini ekrana yazdırın.

    }

}
