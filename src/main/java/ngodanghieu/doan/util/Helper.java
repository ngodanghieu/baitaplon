package ngodanghieu.doan.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Helper  {
    public static String HasPw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }

    public static Boolean CheckPw(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }

    public static String doUpload(String oldFile, MultipartFile file) throws Exception {
        try {
            if (file == null) {
                if (oldFile != null && !oldFile.isEmpty()) return oldFile;
                else return null;
            }

            // delete file
            if (oldFile != null && !oldFile.isEmpty()) {
                delete(new File("src/main/resources/static/images/" + oldFile));
            }

            String uploadRootPath = "src/main/resources/static/images";
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String time = format.format(new Date());

            File uploadRootDir = new File(uploadRootPath + "/" + time);

            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }

            String name = file.getOriginalFilename();

            if (name != null && name.length() > 0) {
                long t = new Date().getTime();
                String fileName = t + "_" + name;
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                return "images/" + time + "/" + fileName;
            }
            throw new Exception("File name invalid");
        } catch (Exception e) {
            throw new Exception("Can not upload file!");
        }
    }

    public static String doUpload2(String oldFile, MultipartFile file) throws Exception {
        try {
            if (file == null) {
                if (oldFile != null && !oldFile.isEmpty()) return oldFile;
                else return null;
            }

            // delete file
            if (oldFile != null && !oldFile.isEmpty()) {
                String uploadRootPath = System.getProperty("user.home") + "/";
                delete(new File(uploadRootPath + oldFile));
            }

            String uploadRootPath = System.getProperty("user.home") + "/images";
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String time = format.format(new Date());

            File uploadRootDir = new File(uploadRootPath + "/" + time);

            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }

            String name = file.getOriginalFilename();

            if (name != null && name.length() > 0) {
                long t = new Date().getTime();
                String fileName = t + "_" + name;
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                return "images/" + time + "/" + fileName;
            }
            throw new Exception("File name invalid");
        } catch (Exception e) {
            throw new Exception("Can not upload file!");
        }
    }

    private static void delete(File file) {
        if (file.isDirectory()) {
            for (File deleteMe : Objects.requireNonNull(file.listFiles())) {
                // recursive delete
                delete(deleteMe);
            }
        }
    }
}
