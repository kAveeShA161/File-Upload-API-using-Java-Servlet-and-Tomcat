package api;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/file")
@MultipartConfig
public class FileUploadAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        String extension = req.getParameter("extension");

        Part filePart = req.getPart("image");
        InputStream stream = filePart.getInputStream();

        boolean isExported = exportFileToSpecificPath(stream,extension,fileName);
        resp.getWriter().write("File Exported success : " + isExported);
    }

    public boolean exportFileToSpecificPath(InputStream stream, String extension, String fileName) throws FileNotFoundException {
        String filePath = System.getProperty("user.home") + "\\OneDrive\\Desktop\\" + fileName + "." + extension;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);;

        String folderPath = System.getProperty("user.home") + "\\OneDrive\\Desktop\\Sample\\";
        filePath = folderPath + fileName + "." + extension;

        java.io.File folder = new java.io.File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs(); // create folder if it doesn't exist
            if (!created) {
                System.err.println("Failed to create the folder: " + folderPath);
                return false;
            }
        }

        try {
            fileOutputStream = new FileOutputStream(filePath);
            int i = 0;
            while ((i = stream.read()) != -1) {
                fileOutputStream.write(i);
            }
            fileOutputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
