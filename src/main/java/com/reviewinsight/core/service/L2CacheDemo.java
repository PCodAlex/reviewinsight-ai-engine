package com.reviewinsight.core.service;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.entity.Project;

public class L2CacheDemo {
	public static void showL2Cache(){

        System.out.println("\n---- SESSION 1 ----");

        Session s1 = HibernateUtil.getSessionFactory().openSession();

        Query<Project> q1 = s1.createQuery("from Project", Project.class);
        q1.setMaxResults(1);
        Project p1 = q1.uniqueResult();

        if(p1 == null){
            System.out.println("❌ No project found");
            s1.close();
            return;
        }

        System.out.println("Project: " + p1.getName());
        Long id = p1.getId();
        s1.close();

        System.out.println("\n---- SESSION 2 ----");

        Session s2 = HibernateUtil.getSessionFactory().openSession();
        Project p2 = s2.get(Project.class, id);
        System.out.println("Project: " + p2.getName());
        s2.close();

        System.out.println("\nIf second session did NOT fire SQL → L2 cache working");
    }
}
