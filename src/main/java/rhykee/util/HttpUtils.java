package rhykee.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HttpUtils {

    private static final String BASE_URL = "https://adventofcode.com/2021/day/";

    public static List<String> getInputForDay(int day, String cookie) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL + day+"/input");
            request.addHeader("cookie", cookie);
            CloseableHttpResponse response = client.execute(request);
            return Arrays.stream(new BasicResponseHandler().handleResponse(response).split("\n"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
