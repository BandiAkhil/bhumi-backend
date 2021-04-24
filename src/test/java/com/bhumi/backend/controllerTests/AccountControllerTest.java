package com.bhumi.backend.controllerTests;

import com.bhumi.backend.controller.AccountController;
import com.bhumi.backend.repository.Post;
import com.bhumi.backend.repository.User;
import com.bhumi.backend.repository.Vote;
import com.bhumi.backend.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    private static final Long TEST_USER_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private User mockUser;
    private Vote mockVote;
    private Post mockPost;

    @BeforeEach
    void setup() {
        mockUser = new User();
        mockUser.setId(TEST_USER_ID);
        mockUser.setEmail("Testemailid@test.com");
        mockUser.setUsername("Test username");
        mockUser.setCreated(LocalDate.now());
        mockUser.setRole("Admin");
        mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setTitle("Test post title");
        mockPost.setBody("Test post body");
        mockPost.setDate(LocalDate.now());
        mockPost.setImageUrl("assets/img/fakeImg.png");
        mockVote = new Vote();
        mockVote.setId(1L);
        mockVote.setUser(mockUser);
        mockVote.setPost(mockPost);
    }

    @Test
    public void testGetAllUserVotes() throws Exception {
        Vote mockVote2 = new Vote(2L, mockPost, mockUser);
        when(this.accountService.getAllUserVotes(mockUser.getId())).thenReturn(Arrays.asList(mockVote, mockVote2));
        mockMvc.perform(get("/api/users/" + mockUser.getId() + "/votes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(mockVote.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(mockVote2.getId().intValue())));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/api/users/" + TEST_USER_ID + "/delete"))
                .andExpect(status().isOk());
    }
}
