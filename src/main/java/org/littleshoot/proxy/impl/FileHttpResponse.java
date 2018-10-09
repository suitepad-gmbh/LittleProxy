package org.littleshoot.proxy.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.annotation.Nonnull;

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

    private boolean isFileNotAccessible() {
        if (file == null) return true;
        try {
            if (!file.exists() || !file.isFile() || !file.canRead()) return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public RandomAccessFile getFile() {
        if (isFileNotAccessible()) return null;
        try {
            return new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
