package me.rodrigoleon.gpt;

import me.rodrigoleon.core.Main;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Gpt extends Main {

    private static String API_KEY;
    private static final String MODEL = "gpt-3.5-turbo";
    private static int MAX_PROMPT_LENGTH;

    public static String Prompt(String prompt) {

        //prompt = (prompt.length() > MAX_PROMPT_LENGTH) ? prompt.substring(0, MAX_PROMPT_LENGTH) : prompt;

        try {
            HttpClient httpClient = HttpClients.createDefault();
            final String URL = "https://api.openai.com/v1/chat/completions";
            HttpPost request = new HttpPost(URL);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            request.setEntity(new StringEntity(body));
            System.out.println(body);

            HttpResponse response = httpClient.execute(request);

            String responseBody = EntityUtils.toString(response.getEntity());

            return extractMessageFromJSONResponse(responseBody);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static void setMaxPromptLength(int maxPromptLength) {
        MAX_PROMPT_LENGTH = maxPromptLength;
    }
}
