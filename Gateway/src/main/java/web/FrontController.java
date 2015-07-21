package web;

import com.google.gson.Gson;
import dto.Calculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import util.CalculationIterator;
import util.PostFixConverter;

/**
 * Created by yoon on 15. 7. 8..
 */
@Controller
public class FrontController {

    Logger log = LoggerFactory.getLogger(FrontController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public @ResponseBody String httpRequest(String requestCalculation) {
        log.info("Request Calculation = {}", requestCalculation);

        try {
            CalculationIterator iterator = new CalculationIterator(new PostFixConverter(requestCalculation));
            int operationResult;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Calculation calculation = iterator.moveToFirst();
            while (calculation != null) {

                //request
                HttpEntity<Calculation> requestEntity = new HttpEntity<Calculation>(calculation, headers);

                //response
                ResponseEntity<String> response = restTemplate.exchange(calculation.getOperator().getServerUrl(), HttpMethod.POST, requestEntity, String.class);
                //HttpHeaders responseHeaders = response.getHeaders();

                operationResult = Integer.parseInt(response.getBody().toString());

                if (iterator.hasNext())
                    calculation = iterator.nextWithPreviousResult(operationResult);
                else {
                    return ""+operationResult;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error : {}", e.getMessage());
            return e.getMessage();
        }

        return "Error";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}