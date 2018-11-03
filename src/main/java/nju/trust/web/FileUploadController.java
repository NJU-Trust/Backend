package nju.trust.web;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.exception.BadRequestException;
import nju.trust.exception.InternalException;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.util.PathUtils;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    private UserRepository userRepository;

    private static String config = "http://193.112.82.110:8000/";

    @RequestMapping("/image")
    public String uploadImage(MultipartFile file) {
        String path = PathUtils.IMAGE_FOLDER_PATH;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty())
            throw new BadRequestException("Image is empty");
        if (filename.contains("..")) {
            // This is a security check
            throw new BadRequestException(
                    "Cannot store file with relative path outside current directory " + filename);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(path).resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return "images/upload/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Upload failed");
        }
    }

    @RequestMapping("/csv")
    public String uploadCsv(MultipartFile file) {
        String path = PathUtils.CSV_FOLDER_PATH;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty())
            throw new BadRequestException("Csv is empty");
        if (filename.contains("..")) {
            // This is a security check
            throw new BadRequestException(
                    "Cannot store file with relative path outside current directory " + filename);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(path).resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return "csv/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Upload failed");
        }
    }

    @RequestMapping("/csvconfirm")
    public void csvConfirm(String filename, String username){
        try {
            User user = userRepository.findByUsername(username).get();
            String name = filename.substring(filename.lastIndexOf('.') + 1, filename.length());
            if (name.equals("csv")) {
                if (user.getAlipayPath() == null) {
                    user.setAlipayPath(filename);
                    userRepository.save(user);
                    if (user.getCardPath() != null) {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("alipay", config + filename)
                                .addFormDataPart("card", config + user.getCardPath())
                                .addFormDataPart("username", username)
                                .build();
                        Request request = new Request.Builder()
                                .url("http://193.112.82.110:8001/process")
                                .post(requestBody)
                                .build();
                        Response response = client.newCall(request).execute();
                        System.out.println(response);
                    }
                }
            } else if ((name.equals("xlsx"))) {
                if (user.getCardPath() == null) {
                    user.setCardPath(filename);
                    userRepository.save(user);
                    if (user.getAlipayPath() != null) {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("alipay", config  + user.getAlipayPath())
                                .addFormDataPart("card", config  + filename)
                                .addFormDataPart("username", username)
                                .build();
                        Request request = new Request.Builder()
                                .url("http://193.112.82.110:8001/process")
                                .post(requestBody)
                                .build();
                        Response response = client.newCall(request).execute();
                        System.out.println(response);
                    }
                }
            } else {
                throw new BadRequestException(
                        "文件类型不对" + filename);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
