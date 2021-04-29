package com.bhumi.backend.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames ={"post_id", "user_id"}), 
                            @UniqueConstraint(columnNames ={"comment_id", "user_id"}),
                            @UniqueConstraint(columnNames ={"forum_id", "user_id"}),
                            @UniqueConstraint(columnNames ={"forum_answer_id", "user_id"})})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Forum forum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_answer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ForumAnswer forumAnswer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Vote() {

    }

    public Vote(Long id, Post post, Comment comment, Forum forum, ForumAnswer forumAnswer, User user) {
        this.id = id;
        this.post = post;
        this.comment = comment;
        this.forum = forum;
        this.forumAnswer = forumAnswer;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public ForumAnswer getForumAnswer() {
        return forumAnswer;
    }

    public void setForumAnswer(ForumAnswer forumAnswer) {
        this.forumAnswer = forumAnswer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote [comment=" + comment + ", forum=" + forum + ", forumAnswer=" + forumAnswer + ", id=" + id
                + ", post=" + post + ", user=" + user + "]";
    }

    public String toJson() {
        return new ObjectMapper().valueToTree(this).toString();
    }
}
