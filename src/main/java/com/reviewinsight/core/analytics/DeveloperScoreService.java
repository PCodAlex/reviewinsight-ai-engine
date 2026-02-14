package com.reviewinsight.core.analytics;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.entity.*;
import com.reviewinsight.core.ai.CommentCategory;

public class DeveloperScoreService {
	public static void generateDeveloperReport(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Developer> developers =
                session.createQuery("from Developer", Developer.class).list();

        System.out.println("\n========= 👨‍💻 DEVELOPER AI REPORT =========\n");

        for(Developer dev : developers){

            int bug = 0;
            int performance = 0;
            int security = 0;
            int positive = 0;

            for(Project p : dev.getProjects()){
                for(PullRequest pr : p.getPullRequests()){
                    for(ReviewComment c : pr.getComments()){

                        if(c.getCategory() == null) continue;

                        switch(c.getCategory()){
                            case BUG_RISK: bug++; break;
                            case PERFORMANCE: performance++; break;
                            case SECURITY: security++; break;
                            case POSITIVE: positive++; break;
                            default: break;
                        }
                    }
                }
            }

            int score = 100 - (bug*10 + performance*5 + security*15) + (positive*5);
            if(score < 0) score = 0;

            System.out.println("Developer: " + dev.getName());
            System.out.println("Bug Issues: " + bug);
            System.out.println("Performance Issues: " + performance);
            System.out.println("Security Issues: " + security);
            System.out.println("Positive Feedback: " + positive);
            System.out.println("AI Score: " + score + "/100");

            if(score >= 80){
                System.out.println("Status: ⭐ Excellent Developer");
            }else if(score >= 50){
                System.out.println("Status: 👍 Good Developer");
            }else{
                System.out.println("Status: ⚠ Needs Improvement");
            }

            System.out.println("-----------------------------------");
        }

        session.close();
    }
}
