package io.kloudwork.util;

/*
 * MultipartFormHandler.java - Parses a multipat form request.
 *
 * Copyright 2017 kloud.software
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

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
    public static MultipartParamHolder handle(Request request) throws IOException, FileUploadException {
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
            return new MultipartParamHolder(postParameters);
        }

        return new MultipartParamHolder(postParameters, files);
    }

    private static MultipartParamHolder parseContent(Request request) {
        request.contentType();
        return null;
    }
}