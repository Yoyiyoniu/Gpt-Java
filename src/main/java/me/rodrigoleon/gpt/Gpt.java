package me.rodrigoleon.gpt;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Gpt {
    private static boolean DEBUGMODE = false;
    private static String API_KEY;
    private static final String MODEL = "gpt-3.5-turbo";

    public static void roll(String personaje, String pregunta) {
        if (DEBUGMODE){
            System.out.println("personaje: " + personaje);
            System.out.println("Pregunta: "+ pregunta);
        }
        System.out.println(Prompt("habla como si fueras " + personaje +" \\n "+ pregunta));
    }

    public static String Prompt(String prompt) {

        try {
            HttpClient httpClient = HttpClients.createDefault();
            final String URL = "https://api.openai.com/v1/chat/completions";
            HttpPost request = new HttpPost(URL);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            request.setEntity(new StringEntity(body));

            HttpResponse response = httpClient.execute(request);

            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println( DEBUGMODE ? body : "debug mode off");
            return extractMessageFromJSONResponse(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }
    public static void DEBUGMODE(boolean debug){
        DEBUGMODE = debug;
    }
}
