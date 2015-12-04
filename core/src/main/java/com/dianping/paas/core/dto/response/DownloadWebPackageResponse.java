package com.dianping.paas.core.dto.response;

import lombok.Data;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
public class DownloadWebPackageResponse extends Response {
    private FileSystemResource fileSystemResource;

    @Override
    public String toString() {
        return "DownloadWebPackageResponse{" +
                "fileSystemResource=" + fileSystemResource +
                "} " + super.toString();
    }
}
