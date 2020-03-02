package ru.job4j.servlets.userservlet;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * UserEditServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2020
 */
public class UserEditServlet extends HttpServlet {
    private static final String DEF_PHOTO = "default.png";
    private static final String PHOTO_ID = "photoId";
    private static final String OLD_PHOTO_ID = "oldPhotoId";
    private static final String IMAGES_FOLDER = "WEB-INF/bin/images";
    private static final Logger LOG = LogManager.getLogger(UserEditServlet.class);
    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("user", service.findById(req));
            req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.update(req);
            req.setAttribute("user", service.findById(req));
            req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    /**
     * Update User. If User have not default image and image update, then old image delete from image`s directory.
     * @param req request
     */
    private void update(HttpServletRequest req) {
        ImageUploader.upload(this, req);
        if (req.getAttribute(PHOTO_ID) != null) {
            File folder = new File(this.getServletConfig().getServletContext().getRealPath(IMAGES_FOLDER));
            String photoId = (String) req.getAttribute(OLD_PHOTO_ID);
            if (!DEF_PHOTO.equals(photoId)) {
                File image = new File(Joiner.on(File.separatorChar).join(folder, photoId));
                if (image.exists() && image.isFile()) {
                    image.delete();
                }
            }
        }
        this.service.update(req);
    }
}
