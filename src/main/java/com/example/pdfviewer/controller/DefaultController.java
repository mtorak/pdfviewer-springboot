package com.example.pdfviewer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/media")
public class DefaultController {

  @GetMapping
  public String home() {
    return "home";
  }

  @GetMapping("preview")
  public void preview(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
//    File file = new File("/home/bentegopersonel/diyasor_workspace/uploaded-engine/9/e95433e8-fb08-4c63-ba57-70331903562f.pdf");
    File file = ResourceUtils.getFile("classpath:doc/Adam Boduch_ Roy Derks - React and React Native_ "
        + "A complete hands-on guide to modern web and mobile development with React.js, 3rd Edition (2020, Packt Publishing Ltd).pdf");
    if (file.exists()) {
      byte[] data = null;
      FileInputStream input = null;

      try {
        input = new FileInputStream(file);
        data = new byte[input.available()];
        input.read(data);

        response.setContentType("application/pdf");
        response.getOutputStream().write(data);
        response.flushBuffer();

      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        try {
          if (input != null) {
            input.close();
          }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }

    }
  }
}




















































