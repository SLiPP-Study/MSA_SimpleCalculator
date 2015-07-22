package component;

import util.Operator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

/**
 * Created by yoon on 15. 7. 21..
 */
public class RestTest {

    RestTemplate restTemplate;
    HttpHeaders headers;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
     public void additionServer() {
        //response
        ResponseEntity<String> response = postForEntity(Operator.ADDITION, createJsonParameter(1, 3));

        assertEquals(4, Integer.parseInt(response.getBody().toString()));
    }

    @Test
    public void subtractionServer() {
        //response
        ResponseEntity<String> response = postForEntity(Operator.SUBTRACTION, createJsonParameter(4, 1));

        assertEquals(3, Integer.parseInt(response.getBody().toString()));
    }

    @Test
    public void multiplicationServer() {
        //response
        ResponseEntity<String> response = postForEntity(Operator.MULTIPLICATION, createJsonParameter(3, 2));

        assertEquals(6, Integer.parseInt(response.getBody().toString()));
    }

    @Test
    public void divisionServer() {
        //response
        ResponseEntity<String> response = postForEntity(Operator.DIVISION, createJsonParameter(10, 2));

        assertEquals(5, Integer.parseInt(response.getBody().toString()));
    }

    private String createJsonParameter(int operand1, int operand2) {
        return "{\"operand1\":"+operand1 + ", \"operand2\": "+operand2+"}";
    }

    private ResponseEntity<String> postForEntity(Operator operator, String jsonParameter) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonParameter, headers);
        return restTemplate.postForEntity(operator.getServerUrl(), requestEntity, String.class);
    }

    @Test
    public void split() {
        String text = "10+20-30*40/50";
        StringTokenizer tokenizer = new StringTokenizer(text, "+-*/", true);
        while(tokenizer.hasMoreTokens())
            System.out.println(tokenizer.nextToken());
        //for (String t : text.split(("(?=\\b[\\+\\-\\*/])"))) {
        //    System.out.println(t);
        //}
    }
}
