package com.reviewinsight.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.util.*;

@Entity
@Table(name = "projects")@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable=false, unique=true)
	    private String name;

	    private String repositoryUrl;

	    // One project → many PR
	    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	    private List<PullRequest> pullRequests = new ArrayList<>();

	    // Many project ↔ many developer
	    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	    @JoinTable(
	            name="project_developers",
	            joinColumns=@JoinColumn(name="project_id"),
	            inverseJoinColumns=@JoinColumn(name="developer_id")
	    )
	    private Set<Developer> developers = new HashSet<>();

	    public Project(){}

	    public Project(String name,String repositoryUrl){
	        this.name=name;
	        this.repositoryUrl=repositoryUrl;
	    }

	    public Long getId(){ return id; }

	    public String getName(){ return name; }
	    public void setName(String name){ this.name=name; }

	    public String getRepositoryUrl(){ return repositoryUrl; }
	    public void setRepositoryUrl(String repositoryUrl){ this.repositoryUrl=repositoryUrl; }

	    public List<PullRequest> getPullRequests(){ return pullRequests; }
	    public void setPullRequests(List<PullRequest> pullRequests){ this.pullRequests=pullRequests; }

	    public Set<Developer> getDevelopers(){ return developers; }
	    public void setDevelopers(Set<Developer> developers){ this.developers=developers; }
}
