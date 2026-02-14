package com.reviewinsight.core.service;

import org.hibernate.Session;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.entity.Project;

public class L1CacheDemo {
	public static void showL1Cache(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.println("\n---- First fetch ----");
        Project p1 = session.get(Project.class, 1L);

        System.out.println("\n---- Second fetch (same session) ----");
        Project p2 = session.get(Project.class, 1L);

        System.out.println("\nCheck console SQL:");
        System.out.println("If only ONE select fired → L1 cache working");

        session.close();
    }
}
