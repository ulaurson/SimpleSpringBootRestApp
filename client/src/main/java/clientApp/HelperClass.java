package clientApp;


import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelperClass {

    private double num1;
    private double num2;
    private String op;

    public HelperClass() {}

    protected void askNumbers() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a first number: ");
        this.num1 = Double.parseDouble(br.readLine());
        System.out.println("Enter a second number: ");
        this.num2 = Double.parseDouble(br.readLine());
        System.out.println("Enter an operation: ");
        this.op = br.readLine();
    }

    protected JSONObject makeJsonObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("op", op);
        jsonObject.put("num1", num1);
        jsonObject.put("num2", num2);
        return jsonObject;
    }

    protected void sendCalculationMessage(JSONObject json){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<String> requestEntity = new HttpEntity<>(json.toString(), requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/calculate",
                HttpMethod.POST, requestEntity, String.class);
        System.out.println("\nCalculation result: "+responseEntity.getBody());
    }


    protected boolean wantToSendNewCalculation() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do you want to send new message? (y/n)");
        String response = br.readLine();
        boolean wantsToSendNewMessage = false;
        if(response.equals("y")){
            wantsToSendNewMessage = true;
        }
        return wantsToSendNewMessage;
    }
}
