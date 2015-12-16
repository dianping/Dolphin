package com.dianping.paas.core.dto.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by yuchao on 12/3/15.
 */
@Data
@ToString(callSuper = true)
public class DownloadWebPackageResponse extends Response {
    private FileSystemResource fileSystemResource;
}
