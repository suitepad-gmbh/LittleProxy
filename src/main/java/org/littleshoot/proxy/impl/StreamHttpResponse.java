package org.littleshoot.proxy.impl;

import java.io.InputStream;

import javax.annotation.Nullable;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * Use this to answer with a file
 */
public class StreamHttpResponse extends DefaultHttpResponse {

    private InputStream stream;

    public StreamHttpResponse(HttpResponseStatus status, InputStream stream, HttpHeaders headers) {
        super(HttpVersion.HTTP_1_1, status, headers);
        this.stream = stream;
    }

    public StreamHttpResponse(HttpResponseStatus status, InputStream stream) {
        super(HttpVersion.HTTP_1_1, status);
        this.stream = stream;
    }

    @Nullable public InputStream getStream() {
        return stream;
    }

}
