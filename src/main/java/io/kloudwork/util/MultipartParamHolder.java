package io.kloudwork.util;

/*
 * MultipartParamHolder.java - Holds a parsed multi part form request.
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

import org.apache.commons.fileupload.FileItemStream;

import java.util.Map;
import java.util.Optional;

public final class MultipartParamHolder {
    private final Map<String, String> parameters;
    private final Optional<Map<String, FileItemStream>> files;

    public MultipartParamHolder(Map<String, String> parameters) {
        this.parameters = parameters;
        this.files = Optional.empty();
    }

    public MultipartParamHolder(Map<String, String> parameters, Map<String, FileItemStream> files) {
        this.parameters = parameters;
        this.files = Optional.of(files);
    }

    public MultipartParamHolder(Map<String, String> parameters, Optional<Map<String, FileItemStream>> files) {
        this.parameters = parameters;
        this.files = files;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Optional<Map<String, FileItemStream>> getFiles() {
        return files;
    }

}
