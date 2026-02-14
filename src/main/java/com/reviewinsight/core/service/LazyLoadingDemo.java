package com.reviewinsight.core.service;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.entity.Project;

public class LazyLoadingDemo {
	public static void showLazyLoading(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.println("\n---- Fetching first project ----");

        Query<Project> q = session.createQuery("from Project", Project.class);
        q.setMaxResults(1);
        Project project = q.uniqueResult();

        if(project == null){
            System.out.println("❌ No project found in DB");
            session.close();
            return;
        }

        System.out.println("Project Name: " + project.getName());

        System.out.println("\n---- Developers NOT fetched yet (LAZY) ----");

        System.out.println("\n---- Access developers now ----");
        System.out.println("Developer count: " + project.getDevelopers().size());

        session.close();
    }
}
