package rest.client;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import tom.Jucator;
import tom.services.rest.ServiceException;

import java.util.concurrent.Callable;

public class JucatorClient {

    public static final String URL = "http://localhost:8080/tom/jucatori";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Jucator[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Jucator[].class));
    }

    public Jucator getById(String id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Jucator.class));
    }

}
