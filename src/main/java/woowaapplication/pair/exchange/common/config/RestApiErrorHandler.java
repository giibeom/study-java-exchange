package woowaapplication.pair.exchange.common.config;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class RestApiErrorHandler extends DefaultResponseErrorHandler {

    @Override
    protected boolean hasError(HttpStatus statusCode) {
        return !statusCode.is2xxSuccessful();
    }


    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        throw new RuntimeException("Api Execute Error " + url.toString() + " " + method.name());
    }
}
