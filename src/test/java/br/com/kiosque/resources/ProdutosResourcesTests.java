package br.com.kiosque.resources;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ProdutosResourcesTests {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/produtos";
	}
	
	@Test
	public void getAllprodutosTest() {
		RestAssured.given()
		.when()
		.get()
		.then()
		.statusCode(200);
		
	}
	
	@Test
	public void saveProdutosTest() {
		RestAssured.given()
		.body("{\r\n"
				+ "\"name\": \"laranja\",\r\n"
				+ "\"image\": \"laranja.jpg\",\r\n"
				+ "\"description\": \"fruta\",\r\n"
				+ "\"value\": 7.00,\r\n"
				+ "\"qr\": \"pixlaranja.jpg\",\r\n"
				+ "\"amount\": 1,\r\n"
				+ "\"unit\": \"quilo\"\r\n"
				+ "}")
		
		.contentType(ContentType.JSON)
		.when()
		.post()
		.then()
		.statusCode(201)
		
		.body("name", Matchers.is("laranja"))
		.body("image", Matchers.is("laranja.jpg"))
		.body("description", Matchers.is("fruta"))
		.body("value", Matchers.is(7.00f))
		.body("qr", Matchers.is("pixlaranja.jpg"))
		.body("amount", Matchers.is(1f))
		.body("unit", Matchers.is("quilo"));
	}
	
	@Test
	public void updatedProdutosTest() {
		RestAssured.given()
		.body("{\r\n"
				+ "\"name\": \"beterraba\",\r\n"
				+ "\"image\": \"beterraba.jpg\",\r\n"
				+ "\"description\": \"verdura\",\r\n"
				+ "\"value\": 4.00,\r\n"
				+ "\"qr\": \"pixbeterraba.jpg\",\r\n"
				+ "\"amount\": 1,\r\n"
				+ "\"unit\": \"quilo\"\r\n"
				+ "}")
		
		.contentType(ContentType.JSON)
		.when()
		.put("produtos/5")
		.then()
		.statusCode(200)
		
		.body("name", Matchers.is("beterraba"))
		.body("image", Matchers.is("beterraba.jpg"))
		.body("description", Matchers.is("verdura"))
		.body("value", Matchers.is(7.00f))
		.body("qr", Matchers.is("pixbeterraba.jpg"))
		.body("amount", Matchers.is(1f))
		.body("unit", Matchers.is("quilo"));		
	}
	
	@Test
	public void deleteProdutosTest() {
		RestAssured.given()
		.contentType(ContentType.JSON)
		.when()
		.delete("produtos/102")
		.then()
		.statusCode(204);
	}

}
