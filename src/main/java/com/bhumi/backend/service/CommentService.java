package com.bhumi.backend.service;

import com.bhumi.backend.dao.CommentDAO;
import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.dto.CommentDTO;
import com.bhumi.backend.entity.Comment;
import com.bhumi.backend.mapper.CommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentDAO commentDAO;
    private final PostDAO postDAO;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentDAO commentDAO, PostDAO postDAO, CommentMapper commentMapper) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getAllPostComments(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        return toThreadedComments(commentDAO.findAllPostComments(postId));
    }

    public List<CommentDTO> toThreadedComments(List<Comment> comments) {
        List<CommentDTO> threaded = new ArrayList<CommentDTO>();        
        List<Comment> removeComments = new ArrayList<Comment>();

        for(int i = 0; i < comments.size(); i++){
            CommentDTO c = commentMapper.EntityToDto(comments.get(i));
            if(c.getParentCommentId() == null){
                c.setCommentDepth(0);
                c.setChildCount(0);
                threaded.add(c);
                removeComments.add(commentMapper.DtoToEntity(c));
            }
        }

        if(removeComments.size() > 0){
            comments.removeAll(removeComments);
            removeComments.clear();
        }

        int depth = 0;
        while(comments.size() > 0){
            depth++;
            for(int j = 0; j < comments.size(); j++){
                CommentDTO child = commentMapper.EntityToDto(comments.get(j));
                for(int i = 0; i < threaded.size(); i++){
                    CommentDTO parent = threaded.get(i);
                    if(parent.getId() == child.getParentCommentId()){
                        parent.setChildCount(parent.getChildCount() + 1);
                        child.setCommentDepth(depth + parent.getCommentDepth());
                        threaded.add(i + parent.getChildCount(),child);
                        removeComments.add(commentMapper.DtoToEntity(child));
                        continue;
                    }
                }
            }
            if(removeComments.size() > 0){
                comments.removeAll(removeComments);
                removeComments.clear();
            }
        }
        return threaded;
    }

    public Comment getCommentById(Long id) {
        return commentDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment by id " + id + " was not found"));
    }

    public CommentDTO getCommentDTOById(Long id) {
        Comment comment = commentDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment by id " + id + " was not found"));
        return commentMapper.EntityToDto(comment);
    }

    @Transactional
    public CommentDTO addComment(CommentDTO commentDTO) {
        if(!postDAO.existsById(commentDTO.getPostId())) {
            throw new RuntimeException("Post with id " + commentDTO.getPostId() + " was not found");
        }
        commentDTO.setUpdated(LocalDate.now());
        Comment commentEntity = commentMapper.DtoToEntity(commentDTO);
        Comment updated = commentDAO.save(commentEntity);
        return commentMapper.EntityToDto(updated);
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        if(!postDAO.existsById(commentDTO.getPostId())) {
            throw new RuntimeException("Post with id " + commentDTO.getPostId() + " was not found");
        }
        commentDTO.setUpdated(LocalDate.now());
        Comment commentEntity = commentMapper.DtoToEntity(commentDTO);
        Comment updated = commentDAO.save(commentEntity);
        return commentMapper.EntityToDto(updated);
    }

    public void deleteCommentById(Long id) {
        if(!commentDAO.existsById(id)) {
            throw new RuntimeException("Comment with id " + id + " was not found");
        }
        commentDAO.deleteById(id);
    }
}
