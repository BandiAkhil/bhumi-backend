package com.bhumi.backend.controllerTests;

import com.bhumi.backend.controller.PostController;
import com.bhumi.backend.repository.Post;
import com.bhumi.backend.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    private static final Long TEST_POST_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private Post mockPost;

    @BeforeEach
    void setup() {
        mockPost = new Post();
        mockPost.setId(TEST_POST_ID);
        mockPost.setTitle("Test post title");
        mockPost.setBody("Test post body");
        mockPost.setDate(LocalDate.now());
        mockPost.setImageUrl("assets/img/fakeImg.png");
    }

    @Test
    public void testGetAllPost() throws Exception {
        Post mockPost2 = new Post(2L, "title2", "body2", LocalDate.now(), "assets/img/fakeImg2.png");
        Post mockPost3 = new Post(3L, "title3", "body3", LocalDate.now(), "assets/img/fakeImg3.png");
        when(this.postService.getAllPosts()).thenReturn(Arrays.asList(mockPost, mockPost2, mockPost3));
        mockMvc.perform(get("/api/posts/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(mockPost.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(mockPost2.getId().intValue())))
                .andExpect(jsonPath("$[2].id", is(mockPost3.getId().intValue())));
    }

    @Test
    public void testGetPostById() throws Exception {
        when(this.postService.getPostById(TEST_POST_ID)).thenReturn(mockPost);
        mockMvc.perform(get("/api/posts/" + TEST_POST_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockPost.getId().intValue())));
    }
}
