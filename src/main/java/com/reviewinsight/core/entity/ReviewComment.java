package com.reviewinsight.core.entity;

import jakarta.persistence.*;
import com.reviewinsight.core.ai.CommentCategory;
import java.time.LocalDateTime;

@Entity
@Table(name="review_comments")
public class ReviewComment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=2000)
    private String commentText;

    private String filePath;
    private int lineNumber;
    private boolean resolved;

    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private CommentCategory category;
    
    public CommentCategory getCategory() {
		return category;
	}

	public void setCategory(CommentCategory category) {
		this.category = category;
	}
	// many comments → one PR
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pr_id")
    private PullRequest pullRequest;

    public ReviewComment(){
        this.createdAt = LocalDateTime.now();
    }

    public ReviewComment(String text,String file,int line){
        this.commentText=text;
        this.filePath=file;
        this.lineNumber=line;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId(){ return id; }

    public String getCommentText(){ return commentText; }
    public void setCommentText(String commentText){ this.commentText=commentText; }

    public String getFilePath(){ return filePath; }
    public void setFilePath(String filePath){ this.filePath=filePath; }

    public int getLineNumber(){ return lineNumber; }
    public void setLineNumber(int lineNumber){ this.lineNumber=lineNumber; }

    public boolean isResolved(){ return resolved; }
    public void setResolved(boolean resolved){ this.resolved=resolved; }

    public LocalDateTime getCreatedAt(){ return createdAt; }

    public PullRequest getPullRequest(){ return pullRequest; }
    public void setPullRequest(PullRequest pullRequest){ this.pullRequest=pullRequest; }
}
