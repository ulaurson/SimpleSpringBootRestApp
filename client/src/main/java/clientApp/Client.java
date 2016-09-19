package clientApp;


import org.json.JSONObject;

import java.io.IOException;

public class Client {

    public static void main(String args[]) throws IOException {

        HelperClass functions = new HelperClass();
        boolean sendNewCalculation = true;

        while(sendNewCalculation) {

            functions.askNumbers();

            JSONObject json = functions.makeJsonObject();

            functions.sendCalculationMessage(json);

            sendNewCalculation = functions.wantToSendNewCalculation();
        }
    }
}
