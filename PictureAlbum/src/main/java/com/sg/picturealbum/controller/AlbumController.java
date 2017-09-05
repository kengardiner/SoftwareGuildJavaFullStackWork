/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.picturealbum.controller;

import com.sg.picturealbum.dao.AlbumDao;
import com.sg.picturealbum.model.Picture;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ward
 */
@Controller
public class AlbumController {
    
    public static final String pictureFolder = "images/";
    private AlbumDao dao;
    
    @Inject
    public AlbumController(AlbumDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHome(Model model) {
        
        List<Picture> pictures = dao.getAllPictures();
        model.addAttribute("pictureList", pictures);
        
        return "home";
    }
    
    @RequestMapping(value="addPictureForm", method=RequestMethod.GET)
    public String displayAddPictureForm() {
        
        return "addPictureForm";
    }
    
    @RequestMapping(value="/addPicture", method=RequestMethod.POST)
    public String addPicture(HttpServletRequest request,
                    Model model,
                    @RequestParam("displayTitle") String displayTitle,
                    @RequestParam("picture") MultipartFile pictureFile) {

        // only save the pictureFile if the user actually uploaded something
        if (!pictureFile.isEmpty()) {
            try {
                // we want to put the uploaded image into the 
                // <pictureFolder> folder of our application. getRealPath
                // returns the full path to the directory under Tomcat
                // where we can save files.
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                // if <pictureFolder> directory is not there, 
                // go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // get the filename of the uploaded file - we'll use the
                // same name on the server.
                String filename = pictureFile.getOriginalFilename();
                // transfer the contents of the uploaded pictureFile to 
                // the server
                pictureFile.transferTo(new File(savePath + filename));

                // we successfully saved the pictureFile, now save a 
                // Picture to the DAO
                Picture picture = new Picture();
                picture.setFilename(pictureFolder + filename);
                picture.setTitle(displayTitle);
                dao.addPicture(picture);

                // redirect to home page to redisplay the entire album
                return "redirect:home";
            } catch (Exception e) {
                // if we encounter an exception, add the error message 
                // to the model and return back to the pictureFile upload 
                // form page
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                return "addPictureForm";
            }
        } else {
            // if the user didn't upload anything, add the error 
            // message to the model and return back to the pictureFile 
            // upload form page
            model.addAttribute("errorMsg", 
                               "Please specify a non-empty file.");
            return "addPictureForm";
        }


    }
}
