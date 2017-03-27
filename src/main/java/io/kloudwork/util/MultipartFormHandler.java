package io.kloudwork.util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import spark.Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MultipartFormHandler {
    public static PostParamHolder handle(Request request) throws IOException, FileUploadException {
        if (!ServletFileUpload.isMultipartContent(request.raw())) {
            return parseContent(request);
        }
        ServletFileUpload servletFileUpload = new ServletFileUpload();
        FileItemIterator iterator = servletFileUpload.getItemIterator(request.raw());

        Map<String, String> postParameters = new HashMap<>();
        Map<String, FileItemStream> files = new HashMap<>();

        while (iterator.hasNext()) {
            FileItemStream fileItem = iterator.next();
            if (fileItem.isFormField()) {
                try (InputStream is = fileItem.openStream()) {
                    postParameters.put(fileItem.getFieldName(), Streams.asString(is));
                }
            } else {
                files.put(fileItem.getFieldName(), fileItem);
            }
        }

        if (files.isEmpty()) {
            return new PostParamHolder(postParameters);
        }

        return new PostParamHolder(postParameters, files);
    }

    private static PostParamHolder parseContent(Request request) {
        request.contentType();
        return null;
    }
}