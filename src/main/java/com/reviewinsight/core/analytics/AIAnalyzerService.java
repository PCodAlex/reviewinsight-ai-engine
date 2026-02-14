package com.reviewinsight.core.analytics;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.reviewinsight.core.config.HibernateUtil;
import com.reviewinsight.core.ai.CommentClassifier;
import com.reviewinsight.core.ai.CommentCategory;
import com.reviewinsight.core.entity.ReviewComment;

public class AIAnalyzerService {
	public static void analyzeAllComments(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query<ReviewComment> q = session.createQuery("from ReviewComment", ReviewComment.class);
        List<ReviewComment> comments = q.list();

        for(ReviewComment c : comments){

            CommentCategory category =
                    CommentClassifier.classify(c.getCommentText());

            c.setCategory(category);
            session.merge(c);

            System.out.println(
                    c.getCommentText() + " → " + category
            );
        }

        tx.commit();
        session.close();

        System.out.println("\n🔥 AI Analysis completed");
    }
}
