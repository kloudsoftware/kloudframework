package io.kloudwork.util;

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
