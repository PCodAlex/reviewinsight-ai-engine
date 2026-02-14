package com.reviewinsight.core.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="developers")
public class Developer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(mappedBy="developers", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();

    // expertise map
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="developer_expertise",
            joinColumns=@JoinColumn(name="developer_id"))
    @MapKeyColumn(name="technology")
    @Column(name="score")
    private Map<String,Integer> expertiseScores = new HashMap<>();

    public Developer(){}

    public Developer(String name,String email){
        this.name=name;
        this.email=email;
    }

    public Long getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }

    public Set<Project> getProjects(){ return projects; }
    public void setProjects(Set<Project> projects){ this.projects=projects; }

    public Map<String,Integer> getExpertiseScores(){ return expertiseScores; }
    public void setExpertiseScores(Map<String,Integer> expertiseScores){ this.expertiseScores=expertiseScores; }
}
