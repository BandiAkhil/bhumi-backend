package com.bhumi.backend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.bhumi.backend.dao.ImageDAO;
import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.dto.ImageDTO;
import com.bhumi.backend.entity.Image;
import com.bhumi.backend.mapper.ImageMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private final ImageDAO imageDAO;
    private final PostDAO postDAO;
    private final ImageMapper imageMapper;

    public ImageService(ImageDAO imageDAO, PostDAO postDAO, ImageMapper imageMapper) {
        this.imageDAO = imageDAO;
        this.postDAO = postDAO;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getPostImages(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        List<Image> images = imageDAO.findAllByPost(postId);
        List<ImageDTO> dtoImages = new ArrayList<>();
        for(Image image: images) {
            image.setData(decompressBytes(image.getData()));
            dtoImages.add(imageMapper.EntityToDto(image));
        }
        return dtoImages;
    }

    public ImageDTO addPostImage(MultipartFile file, long postId, long userId) throws IOException {
        ImageDTO image = new ImageDTO(file.getContentType(), compressBytes(file.getBytes()), postId, userId);
        Image imageEntity = imageMapper.DtoToEntity(image);
        Image resultImage = imageDAO.save(imageEntity);
        return imageMapper.EntityToDto(resultImage);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
    
}
