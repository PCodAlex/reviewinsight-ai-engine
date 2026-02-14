package com.reviewinsight.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.entity.Developer;
import com.reviewinsight.core.entity.Project;
import com.reviewinsight.core.entity.PullRequest;
import com.reviewinsight.core.entity.ReviewComment;
import com.reviewinsight.core.entity.Reviewer;

public class DataInitializer {
	public static void insertSampleData(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {

            // 🔥 check if project already exists
            Query<Project> q = session.createQuery(
                    "from Project where name = :name", Project.class);
            q.setParameter("name", "AI Code Analyzer");

            if(q.uniqueResult() != null){
                System.out.println("⚠ Sample data already exists. Skipping insert.");
                session.close();
                return;
            }

            // project
            Project project = new Project("AI Code Analyzer","github.com/repo1");

            Developer dev1 = new Developer("Akash","akash@gmail.com");
            Developer dev2 = new Developer("Rahul","rahul@gmail.com");

            dev1.getExpertiseScores().put("Java",90);
            dev1.getExpertiseScores().put("Hibernate",85);
            dev2.getExpertiseScores().put("SQL",80);

            project.getDevelopers().add(dev1);
            project.getDevelopers().add(dev2);

            PullRequest pr1 = new PullRequest("Fix memory bug","OPEN");
            pr1.setProject(project);

            Reviewer r1 = new Reviewer("SeniorDev","senior@gmail.com");
            Reviewer r2 = new Reviewer("Lead","lead@gmail.com");

            pr1.getReviewers().add(r1);
            pr1.getReviewers().add(r2);

            ReviewComment c1 = new ReviewComment(
                    "Possible null pointer bug",
                    "UserService.java",
                    45
            );

            c1.setPullRequest(pr1);
            pr1.getComments().add(c1);

            project.getPullRequests().add(pr1);

            session.persist(project);

            tx.commit();
            session.close();

            System.out.println("🔥 Sample data inserted successfully");

        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }
}
