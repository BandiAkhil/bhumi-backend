package com.bhumi.backend.controllerTests;

import com.bhumi.backend.controller.AccountController;
import com.bhumi.backend.repository.Post;
import com.bhumi.backend.repository.User;
import com.bhumi.backend.service.AdminService;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AccountController.class)
public class AdminControllerTest {

    private static final Long TEST_USER_ID = 1L;
    private static final Long TEST_POST_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    private User mockUser;
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
        mockPost.setId(TEST_POST_ID);
        mockPost.setTitle("Test post title");
        mockPost.setBody("Test post body");
        mockPost.setDate(LocalDate.now());
        mockPost.setImageUrl("assets/img/fakeImg.png");
    }

    @Test
    public void testUserCreation() throws Exception {
        when(this.adminService.addUser(any(User.class))).thenReturn(mockUser);
        mockMvc.perform(post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockUser.toJson()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockUser.getId().intValue())))
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andReturn();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User mockUser2 = new User(2L, "emailid@test.com", "username2", null, LocalDate.now(), "User");
        User mockUser3 = new User(3L, "emailid2@test.com", "username3", "password3", LocalDate.now(), "User");
        when(this.adminService.getAllUsers()).thenReturn(Arrays.asList(mockUser, mockUser2, mockUser3));
        mockMvc.perform(get("/api/users/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(mockUser.getId().intValue())))
                .andExpect(jsonPath("$[0].role", is(mockUser.getRole())))
                .andExpect(jsonPath("$[1].id", is(mockUser2.getId().intValue())))
                .andExpect(jsonPath("$[1].password", is(nullValue())))
                .andExpect(jsonPath("$[2].id", is(mockUser3.getId().intValue())))
                .andExpect(jsonPath("$[2].password", is(mockUser3.getPassword())));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(this.adminService.getUserById(TEST_USER_ID)).thenReturn(mockUser);
        mockMvc.perform(get("/api/users/" + TEST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockUser.getId().intValue())));
    }

    @Test
    public void testPutUserById() throws Exception {
        String newUsername = "Testupdatedusername";
        String newPassword = "Testupdatepassword";
        mockUser.setUsername(newUsername);
        mockUser.setPassword(newPassword);
        when(this.adminService.updateUser(any(User.class))).thenReturn(mockUser);
        mockMvc.perform(put("/api/users/" + TEST_USER_ID + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockUser.toJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockUser.getId().intValue())))
                .andExpect(jsonPath("$.username", is(newUsername)))
                .andExpect(jsonPath("$.password", is(newPassword)));
    }

    @Test
    public void testPostCreation() throws Exception {
        when(this.adminService.addPost(any(Post.class))).thenReturn(mockPost);
        mockMvc.perform(post("/api/posts/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockPost.toJson()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockPost.getId().intValue())))
                .andReturn();
    }

    @Test
    public void testPutPostById() throws Exception {
        String newTitle = "Test updated title";
        String newBody = "<p> Test updated body </p>";
        mockPost.setTitle(newTitle);
        mockPost.setBody(newBody);
        when(this.adminService.updatePost(any(Post.class))).thenReturn(mockPost);
        mockMvc.perform(put("/api/posts/" + TEST_POST_ID + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockPost.toJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(mockPost.getId().intValue())))
                .andExpect(jsonPath("$.title", is(newTitle)))
                .andExpect(jsonPath("$.body", is(newBody)));
    }

    @Test
    public void testDeletePostById() throws Exception {
        mockMvc.perform(delete("/api/posts/" + TEST_POST_ID + "/delete"))
                .andExpect(status().isOk());
    }
}
