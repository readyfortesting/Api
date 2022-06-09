package get_http_request.Day15;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadToText;
import utilities.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank01 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
     1) Tüm Customer bilgilerini ekrana yazdırırn.

     2) Tüm Customer SSN lerini ekrana yazdırın.

     3) Tüm Customer SSN lerini text dosyası olarak kaydedin

     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
     */

    @Test
    public void test() throws IOException {
        Customer[] customers;

        //1-SPEC yapariz (bunun icin BaseUrl'e extends yapariz)
        //   http://www.gmibank.com/api/tp-customers
        spec03.pathParam("parametre1", "tp-customers");

        //2-
        Response response = given().headers("Authorization", "Bearer " + generateToken())
                .when().spec(spec03).get("/{parametre1}")
                .then().contentType(ContentType.JSON)
                .extract().response();

       // response.prettyPrint();

        // ObjectMapper'de pojo classa ulasabilmek icin Class Level'da Customen yazip Day15'deki class'a ulasiriz.


        ObjectMapper obj = new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);

        // 1) Tüm Customer bilgilerini ekrana yazdırırn. ekrana yazdirmak icin for kullaniriz.

        for( int i = 0 ; i<customers.length; i ++)
            System.out.println(i+1 + ". Customer: " + customers[i]);

        // 2) Tüm Customer SSN lerini ekrana yazdırın.
        for( int i = 0 ; i<customers.length; i ++)
            System.out.println(i+1 + ". Customer SSN: " + customers[i].getSsn());

      //  3) Tüm Customer SSN lerini text dosyası olarak kaydedin.
        //utilitiy classinda wRITEtOtEXT RESUABLE METHODU CAGIRIRIZ.

        String fileName="src/test/java/get_http_request/Day15/GMIBankText.java";

        WriteToText.saveSSNData(fileName,customers);

    // 4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        // "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın

        List<String> expectedSsnId=new ArrayList<>();
        expectedSsnId.add("531-95-8437");
        expectedSsnId.add("049-43-2360");
        expectedSsnId.add("123-34-3434");

        List<String> actualSsnId= ReadToText.readCustomerSSNList(fileName);

        Assert.assertTrue("SSNLER ESLESMIYOR",actualSsnId.containsAll(expectedSsnId));

    }

}