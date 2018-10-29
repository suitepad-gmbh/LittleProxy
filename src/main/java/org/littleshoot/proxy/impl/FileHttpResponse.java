package org.littleshoot.proxy.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * Use this to answer with a file
 */
public class FileHttpResponse extends DefaultHttpResponse {

    private File file;

    public FileHttpResponse(HttpVersion version, HttpResponseStatus status, @Nonnull File file) {
        super(version, status);
        this.file = file;
    }

    private boolean isFileAccessible() {
        if (file == null) return false;
        try {
            if (file.exists() && file.isFile() && file.canRead()) return true;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Nullable public RandomAccessFile getFile() {
        if (!isFileAccessible()) return null;
        try {
            return new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
