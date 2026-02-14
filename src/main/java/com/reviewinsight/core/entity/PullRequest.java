package com.reviewinsight.core.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="pull_requests")
public class PullRequest {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    private String status; // OPEN, MERGED, CLOSED

	    private LocalDateTime createdAt;

	    // Many PR → one project
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="project_id")
	    private Project project;

	    // One PR → many review comments
	    @OneToMany(mappedBy="pullRequest", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	    private List<ReviewComment> comments = new ArrayList<>();

	    // Many PR ↔ many reviewers
	    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinTable(
	            name="pr_reviewers",
	            joinColumns=@JoinColumn(name="pr_id"),
	            inverseJoinColumns=@JoinColumn(name="reviewer_id")
	    )
	    private Set<Reviewer> reviewers = new HashSet<>();

	    public PullRequest(){
	        this.createdAt = LocalDateTime.now();
	    }

	    public PullRequest(String title,String status){
	        this.title=title;
	        this.status=status;
	        this.createdAt = LocalDateTime.now();
	    }

	    public Long getId(){ return id; }

	    public String getTitle(){ return title; }
	    public void setTitle(String title){ this.title=title; }

	    public String getStatus(){ return status; }
	    public void setStatus(String status){ this.status=status; }

	    public LocalDateTime getCreatedAt(){ return createdAt; }

	    public Project getProject(){ return project; }
	    public void setProject(Project project){ this.project=project; }

	    public List<ReviewComment> getComments(){ return comments; }
	    public void setComments(List<ReviewComment> comments){ this.comments=comments; }

	    public Set<Reviewer> getReviewers(){ return reviewers; }
	    public void setReviewers(Set<Reviewer> reviewers){ this.reviewers=reviewers; }
}
