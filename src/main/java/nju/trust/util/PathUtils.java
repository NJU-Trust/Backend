package nju.trust.util;

import nju.trust.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
public class PathUtils {

    private static final Logger log = LoggerFactory.getLogger("PathUtils");

    public static String getImagePath() {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) path = new File("");

            File upload = new File(path.getAbsolutePath(), "static/images/upload/");
            if (!upload.exists() && !upload.mkdirs())
                log.error("Create image directory failed");
            return upload.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Upload file not found");
        }
    }
}
