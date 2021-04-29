package com.bhumi.backend.mapper;

import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.entity.Vote;
import com.bhumi.backend.service.AdminService;
import com.bhumi.backend.service.CommentService;
import com.bhumi.backend.service.ForumAnswerService;
import com.bhumi.backend.service.ForumService;
import com.bhumi.backend.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    private final PostService postService;
    private final CommentService commentService;
    private final ForumService forumService;
    private final ForumAnswerService forumAnswerService;
    private final AdminService adminService;

    @Autowired
    public VoteMapper(PostService postService, CommentService commentService, ForumService forumService, ForumAnswerService forumAnswerService, AdminService adminService) {
        this.postService = postService;
        this.commentService = commentService;
        this.forumService = forumService;
        this.forumAnswerService = forumAnswerService;
        this.adminService = adminService;
    }

    public Vote DtoToEntity(VoteDTO vote) {
        Vote voteEntity = new Vote();
        voteEntity.setPost((vote.getPostId() == null) ? null : postService.getPostById(vote.getPostId()));
        voteEntity.setComment((vote.getCommentId() == null) ? null : commentService.getCommentById(vote.getCommentId()));
        voteEntity.setForum((vote.getForumId() == null) ? null : forumService.getForumById(vote.getForumId()));
        voteEntity.setForumAnswer((vote.getForumAnswerId() == null) ? null : forumAnswerService.getForumAnswerById(vote.getForumAnswerId()));
        voteEntity.setUser(adminService.getUserById(vote.getUserId()));
        return voteEntity;
    }

    public VoteDTO EntityToDto(Vote vote) {
        VoteDTO voteDTO = modelMapper.map(vote, VoteDTO.class);
        voteDTO.setPostId((vote.getPost() == null) ? null : vote.getPost().getId());
        voteDTO.setCommentId((vote.getComment() == null) ? null : vote.getComment().getId());
        voteDTO.setForumId((vote.getForum() == null) ? null : vote.getForum().getId());
        voteDTO.setForumAnswerId((vote.getForumAnswer() == null) ? null : vote.getForumAnswer().getId());
        voteDTO.setUserId(vote.getUser().getId());
        return voteDTO;
    }
}