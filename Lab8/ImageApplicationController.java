package com.example.springbootimage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@RestController
public class ImageApplicationController {

    @RequestMapping("/")
    public String defaultView() {
        return "Image Processing App is working.";
    }

    @RequestMapping("image/view/{id}")
    public ResponseEntity<byte[]> viewImage(@PathVariable("id") String id) throws Exception{
        return imageProcessorController.return_photo(id);
    }

    @RequestMapping("image/{id}/crop")
    public ResponseEntity<byte[]> cropImage(@PathVariable("id") String id, @RequestParam("x") int x,
                                            @RequestParam("y") int y, @RequestParam("width") int width,
                                            @RequestParam("height") int height) throws Exception{

        return imageProcessorController.imageCrop(id, x, y, width, height);
    }

    @RequestMapping("image/{id}/size")
    public ResponseEntity<ImageSize> sizeOfImage(@PathVariable("id") String id) {
        return imageProcessorController.sizeOfImage(id);
    }

    @RequestMapping(value = "image/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteImage(@PathVariable("id") String id) {
        return imageProcessorController.delete(id);
    }

    @RequestMapping("image/{id}/histogram")
    public ResponseEntity<ImageHistogram> histogramOfImage(@PathVariable("id") String id) {
        return imageProcessorController.histogram(id);
    }

    @RequestMapping("image/add_test_image")
    public String addTestImage() throws Exception{
        BufferedImage originalImage2 = ImageIO.read(new File("C:\\Users\\Adrian\\IdeaProjects\\spring-boot-image\\src\\main\\java\\com\\example\\springbootimage\\mypic.png"));
        return imageProcessorController.addNewImage(originalImage2);
    }

    @RequestMapping(value = "/image/add", method = RequestMethod.POST)
    public String addImage(HttpServletRequest requestEntity) throws Exception {
        return imageProcessorController.setImage(requestEntity.getInputStream());
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
