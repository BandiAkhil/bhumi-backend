package mapperTests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import com.bhumi.backend.dto.CommentDTO;
import com.bhumi.backend.entity.Comment;
import com.bhumi.backend.entity.Post;
import com.bhumi.backend.entity.User;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class CommentMapperTest {
    
    private ModelMapper modelMapper = new ModelMapper();
    private User user;
    private Post post;

    @Before
    public void setUp() {
        System.out.println("BeforeEach");
        user = new User(1L, "email", "username", "password", LocalDate.now(), "USER");
        post = new Post(1L, "title", "body", LocalDate.now(), "mageUrl");
    }

    @Test
    public void whenConvertPostEntityToPostDto() {
        Comment comment = new Comment(6L, "text", post, user, LocalDate.now(), null);
        System.out.println("comment" + comment);
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        System.out.println("commentDTO2" + commentDTO);
        assertEquals(comment.getId(), commentDTO.getId());
    }

    @Test
    public void whenConvertPostDtoToPostEntity() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setText("text");
        commentDTO.setPostId(2L);
        commentDTO.setUserId(1L);
        commentDTO.setUsername("username");
        commentDTO.setDate(LocalDate.now());
        commentDTO.setParentCommentId(null);
        commentDTO.setChildCount(0);
        commentDTO.setCommentDepth(0);
        System.out.println("commentDTO3" + commentDTO);
        Comment commentEntity = modelMapper.map(commentDTO, Comment.class);
        System.out.println(commentEntity);
        commentEntity.setPost(post);
        commentEntity.setUser(user);
        System.out.println(commentEntity);
        assertEquals(commentDTO.getId(), commentEntity.getId());
    }
}
