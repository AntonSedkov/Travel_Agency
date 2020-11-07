package by.epam.tagency.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UploadController.class);

    private static final String NEXT_CONTROLLER = "/controller";

    private static final String ABSOLUTE_PATH = "E:\\Epam_Web\\_Final_Web_Project\\Travel_Agency\\web";

    private static final String UPPER_DIRECTORY = "pics";
    private static final String TOUR_INFO_DIRECTORY = "tours";
    private static final String PERSONAL_DOC_DIRECTORY = "persdoc";
    private static final String TOUR_DOC_DIRECTORY = "tourdoc";

    private static final String TARGET_NEW_TOUR = "newtourinfo";
    private static final String TARGET_PERSONAL_DOC = "newpersdoc";
    private static final String TARGET_TOUR_DOC = "newtourdoc";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<String> uploadTarget = Optional.ofNullable(request.getParameter(AttributeName.UPLOAD_TARGET));
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        if (uploadTarget.isPresent()) {
            String UPLOAD_DIR_DEFAULT = UPPER_DIRECTORY + File.separator;
            String UPLOAD_DIR = switch (uploadTarget.get()) {
                case TARGET_NEW_TOUR -> UPLOAD_DIR_DEFAULT + TOUR_INFO_DIRECTORY;
                case TARGET_PERSONAL_DOC -> UPLOAD_DIR_DEFAULT + PERSONAL_DOC_DIRECTORY;
                case TARGET_TOUR_DOC -> UPLOAD_DIR_DEFAULT + TOUR_DOC_DIRECTORY;
                default -> UPLOAD_DIR_DEFAULT;
            };
            if (!UPLOAD_DIR.equals(UPLOAD_DIR_DEFAULT)) {
                //  String applicationDir = request.getServletContext().getRealPath("");
                String applicationDir = ABSOLUTE_PATH;
                String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
                File fileSaveDir = new File(uploadFileDir);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                Part part = request.getPart(AttributeName.IMAGE_CONTENT);
                try {
                    String imageName = part.getSubmittedFileName();
                    part.write(uploadFileDir + imageName);
                    request.setAttribute(AttributeName.IMAGE_NAME, imageName);
                    request.setAttribute(AttributeName.UPLOAD_RESULT, true);
                    page = NEXT_CONTROLLER;
                } catch (IOException e) {
                    request.setAttribute(AttributeName.UPLOAD_RESULT, false);
                }
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}