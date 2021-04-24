package com.bhumi.backend.controllerTests;

import com.bhumi.backend.repository.Post;
import com.bhumi.backend.repository.User;
import com.bhumi.backend.repository.Vote;
import com.bhumi.backend.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest {

    private static final Long TEST_VOTE_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    private Vote mockVote;
    private Post mockPost;
    private User mockUser1;
    private User mockUser2;

    @BeforeEach
    void setup() {
        mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setTitle("Test post title");
        mockPost.setBody("Test post body");
        mockPost.setDate(LocalDate.now());
        mockPost.setImageUrl("assets/img/fakeImg.png");
        mockUser1 = new User();
        mockUser1.setId(1L);
        mockUser1.setEmail("Testemailid@test.com");
        mockUser1.setUsername("Test username");
        mockUser1.setCreated(LocalDate.now());
        mockUser1.setRole("Admin");
        mockUser2 = new User();
        mockUser2.setId(2L);
        mockUser2.setEmail("Testemailid@test.com");
        mockUser2.setUsername("Test username");
        mockUser2.setCreated(LocalDate.now());
        mockUser2.setRole("User");
        mockVote = new Vote();
        mockVote.setId(TEST_VOTE_ID);
        mockVote.setUser(mockUser1);
        mockVote.setPost(mockPost);
    }

    @Test
    public void testVoteCreation() throws Exception {
        when(this.voteService.addVote(mockVote)).thenReturn(mockVote);
        mockMvc.perform(post("/api/posts/" + mockPost.getId() + "votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockVote.toJson()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockVote.getId().intValue())))
                .andExpect(jsonPath("$.post", is(mockVote.getPost())))
                .andExpect(jsonPath("$.user", is(mockVote.getUser())))
                .andReturn();
    }

    @Test
    public void testGetAllPostVoteCount() throws Exception {
        Vote mockVote2 = new Vote(2L, mockPost, mockUser2);
        when(this.voteService.getPostVoteCount(mockPost.getId())).thenReturn(Arrays.asList(mockVote, mockVote2).size());
        mockMvc.perform(get("/api/posts/" + mockPost.getId() + "/votes/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(Arrays.asList(mockVote, mockVote2).size())));
    }

    @Test
    public void testDeletePostById() throws Exception {
        mockMvc.perform(delete("/api/posts/" + TEST_VOTE_ID + "/delete"))
                .andExpect(status().isOk());
    }
}
