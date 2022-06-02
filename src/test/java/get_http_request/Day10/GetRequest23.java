package get_http_request.Day10;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
 }

  olduğunu test edin.
*/
    // dummyTestData sayfasindaki islemlerden sonra bu sayyfaya GELDIK
    @Test
    public void test23(){
         //http://dummy.restapiexample.com/api/v1/employees
        //1- URL OLUSTUR
        spec02.pathParams("bir","api","iki","v1","uc","employees");

        //2 OBJE OLUSTUR
        DummyTestData expectedObje=new DummyTestData();
        HashMap<String,Object> expectedTestDataMap=expectedObje.setUpTestData();
        System.out.println("ExpectedTestData = " + expectedTestDataMap);
 /*
 ExpectedTestData = {arananYaslar=[40, 21, 19],
  sondanUcuncuCalisaninMaasi=675000,
  calisanSayisi=24,
  onuncuCalisanBilgileri={profile_image=,
   employee_name=Sonya Frost,
    employee_salary=103600,
    id=10,
    employee_age=23},
    statusCode=200,
     ondorduncuCalisan=Haley Kennedy}

  */

        //3 rEQUEST ve RESPONSE olusturma
        Response response=given().spec(spec02).contentType(ContentType.JSON).when().get("/{bir}/{iki}/{uc}");
        //Response alip alamadigimizi kontrol etmek icin :>>>>   response.prettyPrint();
        response.prettyPrint();

        //4- DOGRULAMA
        //De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("ActualDataMap = " + actualDataMap);

        //1. YOL

       // Status kodun 200 olduğunu, (TestData'ya ulasip dogrulama yapmamiz gerekiyor)

        Assert.assertEquals(expectedTestDataMap.get("statusCode"), response.getStatusCode());

      //  14. Çalışan isminin "Haley Kennedy" olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("ondorduncucalisan"),
                ((Map)((List)actualDataMap.get("data")).get(13)).get("employee_name"));

        // Çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("calisansayisi"),
                ((List<?>) actualDataMap.get("data")).size());


        // Sondan 3. çalışanın maaşının 675000 olduğunu
        //1. Yol
        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List)actualDataMap.get("data")).get(((List)actualDataMap.get("data")).size()-3)).get("employee_salary"));
       //2. Yol

        int dataSize = ((List<?>) actualDataMap.get("data")).size();

        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List<?>) actualDataMap.get("data")).get(dataSize-3)).get("employee_salary"));


        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        //1. Yol
        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i =0; i<dataSize; i++){
            actualYasListesi.add(((Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));

        //2. Yol
        List<Integer> employee_age = new ArrayList<>();
        for(int i=0 ; i < ((List)actualDataMap.get("data")).size() ; i++){
            employee_age.add((Integer) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(employee_age.containsAll((Collection<?>) expectedTestDataMap.get("arananYaslar")));
    }
}

