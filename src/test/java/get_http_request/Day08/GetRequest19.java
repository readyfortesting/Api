package get_http_request.Day08;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees
1) Status kodunun 200,
2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
*/

    @Test
    public void test19(){
        //Step 1: URL olustur
        //http://dummy.restapiexample.com/api/v1/employees
        spec02.pathParams("1","api","2","v1","3","employees");

        //Step 2: Response olustur(Gonderecegimiz requestin adresini tanimliyoruz)
       //   http://dummy.restapiexample.com Requesten onceki adresimiz
        Response response=given().spec(spec02).when().get("/{1}/{2}/{3}");
        // "/{1}/{2}/{3}"   =>>>> /api/v1/employees  bunu eklemis olduk.

        //yazdirmak icin
      //  response.prettyPrint();

        //Status cod  200 oldugunu kontrol edecegiz.
        Assert.assertEquals(200,response.statusCode());
        response.then().assertThat().statusCode(200);


        // 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan buyuk id oldugunu
        JsonPath json=response.jsonPath();
        //groovy language >"    .findAll{it.id>10}" (id'si 10'dan buyuk olanlari getir demek)(if yerine kullanilabilir) gostermek icin bu ornegi yaptik
        List<Integer> idList=json.getList("data.findAll{it.id>10}.id");
        System.out.println("ID List : " + idList);

        //Groovy Java platformu uzerinde calisan bir bilgisayar dilidir.
        // Groovy ile loop kullanmadan response'dan gelen degerleri bir sarta gore alabiliriz.


        // 30'dan kucuk tum yaslari ekrana yazdiriniz ve bu yaslarin icerisinde
        //en buyuk yasin 23 oldugunu gosterin
        List<Integer> yasListesi=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("Yas Listesi = " + yasListesi);

        Collections.sort(yasListesi);
        Assert.assertEquals(23,(int)yasListesi.get(yasListesi.size()-1));
     //   Assert.assertEquals((Integer) 23,yasListesi.get(yasListesi.size()-1));


        // 4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        //     ve bunların içerisinde "Charde Marshall" olduğunu test edin.
        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));
    }
}

