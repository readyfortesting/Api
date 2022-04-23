package practise.Api.Day01;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestP02 {

    /*  Task02 :>> https://restfull-booker.herokuapp.com/booking  url'ine
    -> accept type'i "application/json" olan GET requesti yolladigimda helen response'un
    => Status Code'un 200
    => Content Type'in "application/json" oldugunu test edin.   */


    @Test
    public void test02(){

        // Step 1- URL (Endpoint) String formatinda URL (Endpoint) olusturulur.
        String url="https://restfull-booker.herokuapp.com/booking";

        // Step 2- Expected result olusturulur. (Task2'de expected resul istenmedigi icin yapmiyoruz)

        // Step 3- Request gonderilir.
        Response response = given().
                accept("application/json").
                when().
                get(url);

        // Olusturdugumuz Response'u gormek icin.
        response.prettyPrint();

        // 4- Actual result olusturulur.

        //   5- Assertion (Dogrulama yapilir).
        // => Status Code'un 200
        System.out.println("Status Code : "+ response.getStatusCode());
        // => Content Type'in "application/json"
        System.out.println("Content type : "+response.getContentType());






    }


}
