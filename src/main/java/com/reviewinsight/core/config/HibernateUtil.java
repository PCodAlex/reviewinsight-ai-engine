package com.reviewinsight.core.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.reviewinsight.core.entity.Developer;
import com.reviewinsight.core.entity.Project;
import com.reviewinsight.core.entity.PullRequest;
import com.reviewinsight.core.entity.ReviewComment;
import com.reviewinsight.core.entity.Reviewer;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

    static {
        try {
        	Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            // 🔥 REGISTER ENTITIES HERE
            cfg.addAnnotatedClass(Project.class);
            cfg.addAnnotatedClass(Developer.class);
            cfg.addAnnotatedClass(PullRequest.class);
            cfg.addAnnotatedClass(ReviewComment.class);
            cfg.addAnnotatedClass(Reviewer.class);

            sessionFactory = cfg.buildSessionFactory();

            System.out.println("✅ Hibernate SessionFactory created");

        } catch (Throwable ex) {
            System.out.println("❌ SessionFactory creation failed");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
