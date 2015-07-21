package component;

import util.Operator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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
    public void AdditionServer() {
        //request
        HttpEntity<String> requestEntity = new HttpEntity<String>(getParameter(1, 3), headers);

        //response
        ResponseEntity<String> response = restTemplate.postForEntity(Operator.ADDITION.getServerUrl(), requestEntity, String.class);

        assertEquals(4, Integer.parseInt(response.getBody().toString()));
    }

    private String getParameter(int operand1, int operand2) {
        return "{\"operand1\":\""+operand1 + "\", \"operand2\": \""+operand2+"\"}";
    }
}
