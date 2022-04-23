package practise.Api.Day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequestP01 {

    /* TestCase01
    https://restful-booker.herokuapp.com/booking/3      <= (bu bir ENDPOINT)
    adresine gonderildiginde donecek cevap (response) icin

    => HTTP status kocunun 200
    => Content Type'in Json
    => Status Line'in HTTP/1.1 200 OK

    oldugunu test edin.    */

    // =>  response.prettyPrint(); Response body'i consolda goruntulememizi saglar.

    // Step 1 => https://restful-booker.herokuapp.com/booking/3
    // bu ENDPOINT kopyalanip POSTMAN'de GET request yapilir.
/*
{
    "firstname": "Mark",
    "lastname": "Brown",
    "totalprice": 884,
    "depositpaid": true,           <<====  RESPONSE BODY.  (Postman'de Request sonucu bize Response body dondurdu.)
    "bookingdates": {
        "checkin": "2017-06-09",
        "checkout": "2020-03-20"
    },
    "additionalneeds": "Breakfast"
}
 */
    // id'si 3 olan bu kisinin rezervasyon bilgileri POSTMAN'de listelendi.


    //STEP 2  => HTTP status kodunun POSTMAN'de 200 oldugunu gorduk.
    //STEP 3 => Content Type'in Json oldugunu dogrulamak icin sirayla POSTMAN'de Headers kisminda kontrol edilir.
    //STEP 4 => Status Line'in HTTP/1.1 200 OK  :>> POSTMAN'de Headers kisminda kontrol edilir.

    /* -------------- API Testi Yaparken ---------------
      1- URL (Endpoint) ilk olarak olusturulur.
      2- Expected result olusturulur. (Beklenen sonuc)    (TestCase01'de BODY dogrulamasi istenmedigi icin simdilik expected result olusturmuyoruz)
      3- Request gonderilir.
      4- Actual result olusturulur. (Postmanda bize Request sonucu Response dondurur buna ACTUAL Result da denir)
      5- Assertion (Dogrulama yapilir.)


     */
    @Test
    public void test01(){

        // 1- ilk olarak URL (Endpoint) String formatinda URL (Endpoint) olusturulur.

        String url="https://restful-booker.herokuapp.com/booking/3";

        //2- Expected result olusturulur. (Beklenen sonuc) tESTcASE01' istenmedigi icin simdilik atladik.

        //  3- Request gonderilir. Bunu yaparken Gherkin dilini kullaniriz. Ilk olarak, GEREKLILIKLERI belirten given yazariz.
        //accept("application/jason" VEYA ContentType.JSON) =>> Gelen data'nin formatini Jsomn olarak kabul et demek!
        // .get() => Postman'daki HTTP Request olan GET ve icine String olarak atadigimiz  url'i kopyalariz.
        // given().accept("application/jason").when().get(url); bu olusturdugumum Request'i bir yerde saklamamiz gerekir.
        //Response response= isminde deger olusturup  bunu ekleriz. given().accept("application/jason").when().get(url);
        //Response POSTMAN'daki body, headers, status code hepsini tutabilir.
        Response response= given().when().get(url);  // <<=== Request olusturduk
               // accept(ContentType.JSON).
              //  when().
              //  get(url);

        // Olusturdugumuz Response'u gormek icin  =>> prettyPrint() methodunu kullaniriz.
     //   response.prettyPrint();


        // 4- Actual result olusturulur.
        //Response body ile ilgili birsey sorulmadigi icin simdilik yapmayacagiz.


        //   5- Assertion (Dogrulama yapilir).
               // => HTTP status kocunun 200
              //  => Content Type'in Json
              //  => Status Line'in HTTP/1.1 200 OK

      //  response.getStatusCode();
     //   response.getContentType();
     //   response.getStatusLine();

// bunlari YAZDIRMAK ICIN sout icine atmamiz gerekir.
      System.out.println("StatusCode = " + response.getStatusCode()); // Response'dan gelen : StatusCode = 200
      System.out.println("ContentType = "+ response.getContentType()); // Response'dan gelen : ContentType = application/json; charset=utf-8
      System.out.println("StatusLine = " + response.getStatusLine());  // Response'dan gelen : StatusLine = HTTP/1.1 200 OK

 //headers'in tamamini yazdirmak icin
        System.out.println("Headers = " + response.getHeaders());

        // expected kismi bize task olarak verilen degerdir, actual kismi ise response'dan gelen degerdir.
        // statusCode int deger dondurur.

        //  ----------Klasik  Herzaman kullandigimiz ASSERTION ----------
        //**** Burdaki ASSERT klasik daha once kullandigimiz dogrulama yontemi, bir de Response'dan gelen Assert var.
      //  Assert.assertEquals(200,response.statusCode());
     //   Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
     //   Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        //  ----------RESPONSE'dan gelen ASSERTION Yontemi ----------//
        //assertThat()'den sonra neleri Assert etmek istiyorsak onlari yazariz.

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON). // accept(application/jason) da kullanilabilir.
                statusLine("HTTP/1.1 200 OK");




    }




}
