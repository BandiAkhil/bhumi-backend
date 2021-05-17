package com.bhumi.backend.service;

import com.bhumi.backend.dao.*;
import com.bhumi.backend.dto.ImageDTO;
import com.bhumi.backend.entity.*;
import com.bhumi.backend.mapper.ImageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static java.util.Collections.singletonList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class AdminService implements UserDetailsService {

    private final PostDAO postDAO;
    private final CommentDAO commentDAO;
    private final ForumDAO forumDAO;
    private final ForumAnswerDAO forumAnswerDAO;
    private final VoteDAO voteDAO;
    private final UserDAO userDAO;
    private final ImageDAO imageDAO;
    //private final ImageMapper imageMapper;

    @Autowired
    public AdminService(PostDAO postDAO, CommentDAO commentDAO, ForumDAO forumDAO, ForumAnswerDAO forumAnswerDAO, VoteDAO voteDAO, UserDAO userDAO, ImageDAO imageDAO) {
        this.postDAO = postDAO;
        this.commentDAO = commentDAO;
        this.forumDAO = forumDAO;
        this.forumAnswerDAO = forumAnswerDAO;
        this.voteDAO = voteDAO;
        this.userDAO = userDAO;
        this.imageDAO = imageDAO;
    }

    @Transactional
    public Post addPost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    @Transactional
    public Post updatePost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    public void deletePostById(Long id) {
        postDAO.deleteById(id);
    }

    public void deleteCommentById(Long id) {
        if(!commentDAO.existsById(id)) {
            throw new RuntimeException("Comment with id " + id + " was not found");
        }
        commentDAO.deleteById(id);
    }

    public void deleteForumById(Long id) {
        forumDAO.deleteById(id);
    }

    public void deleteForumAnswerById(Long id) {
        forumAnswerDAO.deleteById(id);
    }

    public List<Vote> getAllPostVotes(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        return voteDAO.findAllByPost(postId);
    }

    public List<com.bhumi.backend.entity.User> getAllUsers() {
        return userDAO.findAll();
    }

    public com.bhumi.backend.entity.User getUserById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User by id " + id + " was not found"));
    }

    public com.bhumi.backend.entity.User getUserByUsername(String username) {
        return userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User by username " + username + " was not found"));
    }

    public com.bhumi.backend.entity.User getUserByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new RuntimeException("User by email " + email + " was not found"));
    }

    public com.bhumi.backend.entity.User getUserByUsernameOrEmail(String username, String email) {
        return userDAO.findByUsernameOrEmail(username, email).orElseThrow(() -> new RuntimeException("User by email or username " + username + " was not found"));
    }

    public com.bhumi.backend.entity.User addUser(com.bhumi.backend.entity.User user) {
        user.setRole("USER");
        user.setCreated(LocalDate.now());
        return userDAO.save(user);
    }

    public com.bhumi.backend.entity.User updateUser(com.bhumi.backend.entity.User user) {
        user.setRole("USER");
        com.bhumi.backend.entity.User original = getUserById(user.getId());
        user.setCreated(original.getCreated());
        return userDAO.save(user);
    }

    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<com.bhumi.backend.entity.User> userOptional = userDAO.findByUsernameOrEmail(username, username);
        System.out.println("AAAHAHAHAHAHAHAAHAHAH :::::: " + username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println("AAAHAHAHAHAHAHAAHAHAH :::::: " + user);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
	}

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

    // public ImageDTO getUserImage(Long userId) {
    //     if(!userDAO.existsById(userId)) {
    //         throw new RuntimeException("Comment with id " + userId + " was not found");
    //     }
    //     Image image = imageDAO.findByUser(userId).get(0);
    //     image.setData(decompressBytes(image.getData()));
    //     ImageDTO dtoImage = imageMapper.EntityToDto(image);
    //     return dtoImage;
    // }

    // public ImageDTO addUserImage(MultipartFile file, long postId, long userId) throws IOException {
    //     ImageDTO image = new ImageDTO(file.getContentType(), compressBytes(file.getBytes()), postId, userId);
    //     Image imageEntity = imageMapper.DtoToEntity(image);
    //     Image resultImage = imageDAO.save(imageEntity);
    //     return imageMapper.EntityToDto(resultImage);
    // }

    // public void deleteImage(Long id) {
    //     if(!imageDAO.existsById(id)) {
    //         throw new RuntimeException("Vote with id " + id + " was not found");
    //     }
    //     imageDAO.deleteById(id);
    // }

    // public static byte[] compressBytes(byte[] data) {
    //     Deflater deflater = new Deflater();
    //     deflater.setInput(data);
    //     deflater.finish();
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    //     byte[] buffer = new byte[1024];
    //     while (!deflater.finished()) {
    //         int count = deflater.deflate(buffer);
    //         outputStream.write(buffer, 0, count);
    //     }
    //     try {
    //         outputStream.close();
    //     } catch (IOException e) {
    //     }
    //     return outputStream.toByteArray();
    // }

    // public static byte[] decompressBytes(byte[] data) {
    //     Inflater inflater = new Inflater();
    //     inflater.setInput(data);
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    //     byte[] buffer = new byte[1024];
    //     try {
    //         while (!inflater.finished()) {
    //             int count = inflater.inflate(buffer);
    //             outputStream.write(buffer, 0, count);
    //         }
    //         outputStream.close();
    //     } catch (IOException ioe) {
    //     } catch (DataFormatException e) {
    //     }
    //     return outputStream.toByteArray();
    // }
}
