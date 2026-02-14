package com.reviewinsight.core.entity;


import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="reviewers")
public class Reviewer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(mappedBy="reviewers", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<PullRequest> pullRequests = new HashSet<>();

    public Reviewer(){}

    public Reviewer(String name,String email){
        this.name=name;
        this.email=email;
    }

    public Long getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }

    public Set<PullRequest> getPullRequests(){ return pullRequests; }
    public void setPullRequests(Set<PullRequest> pullRequests){ this.pullRequests=pullRequests; }
}
