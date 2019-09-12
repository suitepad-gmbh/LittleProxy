package org.littleshoot.proxy.impl;

import java.io.InputStream;

import javax.annotation.Nullable;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * Use this to answer with a file
 */
public class StreamHttpResponse extends DefaultHttpResponse {

    private InputStream stream;

    public StreamHttpResponse(HttpVersion version, HttpResponseStatus status, InputStream stream) {
        super(version, status);
        this.stream = stream;
    }

    @Nullable public InputStream getStream() {
        return stream;
    }

}
