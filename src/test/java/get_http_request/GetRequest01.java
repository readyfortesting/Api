package get_http_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    /* POM xml de 3 dependency kurduk
    - swagger dokumantasyonunda verilecek adresleri test ederiz
    - status code 200 > isler yolunda,herkes siteye ulasabiliyor demek.
    - Response olarak gondeririz ve gelen databaseden gelen bilgileri response classina atariz ve
    response classinda pretyPrint() methodu ile yazdirabiliriz.


     */
    @Test
    public void test01(){
        String url="https://restful-booker.herokuapp.com/booking"; //swagger dokumantosyanda ulasacagim adresi tanimladim
     //   String url="https://restful-booker.herokuapp.com/booking/2"; 2 numarayi getir demek icin boyle yapabiliriz..

      //  given().when().get(url); // TALEP ilk testimizi yapmis olduk, url'e gitti ve o bilgiye ulasmamiz lazim. REQUEST YAPTIK
        Response response=given().when().get(url); //CEVAP //databaseden api kanaliyla donen bilgileri yazdiracak boylelikle RESPONSE icine cevabi ALIRIZ
        response.prettyPrint(); // Response icindeki bilgileri yazdirmak icin bu methodu kullaniriz.

        //status Code getirmek icin (Amazcimiz status code HERZAMAN 200 olmasi)
        System.out.println("Status Code: "+ response.statusCode());

        //content type
        System.out.println("Content Type: "+ response.contentType());

        // Status line
        System.out.println("Status Line : " +response.statusLine());

        //time
        System.out.println("Test zamani : "+response.time());


          // STATUS CODE DOGRULAMAK ICIN
        Assert.assertEquals(200, response.statusCode()); // expectet "" tirnak icine almiyoruz yoksa HATA VERIR.

        Assert.assertEquals("application/json; charset=utf-8", response.contentType());

        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());

        //ASSERTIONLARI tek satirda yapabiliriz.
        response // Responseun kullandigi assertThat() hard assertion kullanir
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");





    }
}
