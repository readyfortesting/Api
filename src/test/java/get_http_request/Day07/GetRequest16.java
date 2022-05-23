package get_http_request.Day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {

     /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    // 1= base_url'e  extends yapilir.

    @Test
    public void test16(){
        //2=  URL olusturulur.
        spec04.pathParams("bir","todos","iki",7);

        //3. Expected (Beklenen) data olustur.
        Map<String, Object> expectedData=new HashMap<>();
      //olusturulan Map'e data girisi icin put kullanilir.
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);

        System.out.println("Expected Data:"+ expectedData);

        //4- Request ve Response olurturulur.
       Response response= given().spec(spec04).when().get("/{bir}/{iki}");
        // "/{bir}/{iki}  adrese bunu ekle. /todos/7
        // https://jsonplaceholder.typicode.com/todos/7

        //yazdiralim
        response.prettyPrint();

        //Response'u alabilmek icin MAP olustururuz.Yani Response'dan gelen Json'i Map icine aliriz.
        Map<String,Object> actualData=response.as(HashMap.class); //De=Serialization yapmis olduk.
        System.out.println("ACTUAL DATA: " + actualData);

        //Actual Data :{id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));



    }
}







