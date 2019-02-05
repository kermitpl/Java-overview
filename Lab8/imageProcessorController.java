package com.example.springbootimage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class imageProcessorController {

    private static Map<String, BufferedImage> imageMap;

    private static Integer id = 0;

    public static ResponseEntity<byte[]> return_photo(String key) throws IOException {
        if (imageMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (imageMap.containsKey(key)) {
            BufferedImage originalImage = imageMap.get(key);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageInByte, headers, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<byte[]> imageCrop(String id, int x, int y, int width, int height) throws IOException {
        if (imageMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (!imageMap.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (x+width < imageMap.get(id).getWidth() && y+height < imageMap.get(id).getHeight()){

            BufferedImage original = imageMap.get(id);
            BufferedImage cropped = original.getSubimage(x, y, width, height);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(cropped, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageInByte, headers, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static ResponseEntity<ImageSize> sizeOfImage(String id) {
        if (imageMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (!imageMap.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            ImageSize size = new ImageSize(imageMap.get(id).getHeight(),imageMap.get(id).getWidth());
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new ResponseEntity<>(size, headers, HttpStatus.CREATED);
        }
    }

    public static ResponseEntity<String> delete(String id) {
        if (imageMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (!imageMap.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            imageMap.remove(id);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);

            return new ResponseEntity<>("Deleted image with ID:"+id, headers, HttpStatus.CREATED);
        }
    }

        public static ResponseEntity<ImageHistogram> histogram(String id) {
        if (imageMap == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (!imageMap.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            BufferedImage image = imageMap.get(id);
            Raster raster = image.getRaster();
            final int w = image.getWidth();
            final int h = image.getHeight();
            double[] r = new double[w * h];
            double[] g = new double[w * h];
            double[] b = new double[w * h];
            r = raster.getSamples(0, 0, w, h, 0, r);
            g = raster.getSamples(0, 0, w, h, 1, g);
            b = raster.getSamples(0, 0, w, h, 2, b);

            ImageHistogram histogram = new ImageHistogram(r, g, b);

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new ResponseEntity<>(histogram, headers, HttpStatus.CREATED);
        }
    }

    public static String addNewImage(BufferedImage image) {
        if (imageMap == null) {
            imageMap = new HashMap<>();
        }

        id++;
        String name = "" +id;
        imageMap.put(name, image);

        return name;
    }

    public static String setImage(ServletInputStream inputStream) throws Exception{
        BufferedImage imageFromStream = ImageIO.read(inputStream);
        return addNewImage(imageFromStream);
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
