package com.carhouse.conrtoller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelTypeControllerTest {

    public static final String RESPONSE_BODY = "[{\"fuelTypeId\":1,\"fuelType\":\"Bensin\"},{\"fuelTypeId\":2," +
            "\"fuelType\":\"Diesel\"},{\"fuelTypeId\":3,\"fuelType\":\"Gasoline\"},{\"fuelTypeId\":4,\"fuelType\":" +
            "\"Electric\"},{\"fuelTypeId\":5,\"fuelType\":\"Bensin\"},{\"fuelTypeId\":6,\"fuelType\":\"Diesel\"}," +
            "{\"fuelTypeId\":7,\"fuelType\":\"Gasoline\"},{\"fuelTypeId\":8,\"fuelType\":\"Electric\"}]";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8088);

    @Test
    public void testMethod() throws IOException {
        stubFor(get(urlEqualTo("/carSale/car/fuelType"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)
                )
        );

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8088/carSale/car/fuelType");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);

        verify(getRequestedFor(urlEqualTo("/carSale/car/fuelType")));
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(RESPONSE_BODY, stringResponse);
    }

    private String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }
}
