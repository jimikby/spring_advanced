package com.epam.theatre.rest.client.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import com.google.common.collect.Lists;

public class AcceptHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String headerValue;

    public AcceptHeaderHttpRequestInterceptor(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().setAccept(Lists.newArrayList(MediaType.valueOf(headerValue)));

        return execution.execute(requestWrapper, body);
    }

}
