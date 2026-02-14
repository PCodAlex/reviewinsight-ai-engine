package com.reviewinsight.core.ai;

import java.util.*;

public class CommentClassifier {
	private static final Map<CommentCategory, List<String>> rules = new HashMap<>();

    static {

        rules.put(CommentCategory.BUG_RISK, Arrays.asList(
                "null", "exception", "crash", "fail", "bug", "memory leak"
        ));

        rules.put(CommentCategory.PERFORMANCE, Arrays.asList(
                "slow", "optimize", "performance", "n+1", "cache", "inefficient"
        ));

        rules.put(CommentCategory.SECURITY, Arrays.asList(
                "sql injection", "xss", "auth", "token", "encrypt", "security"
        ));

        rules.put(CommentCategory.CODE_STYLE, Arrays.asList(
                "format", "indent", "naming", "refactor", "clean code"
        ));

        rules.put(CommentCategory.ARCHITECTURE, Arrays.asList(
                "design", "architecture", "pattern", "coupling", "layer"
        ));

        rules.put(CommentCategory.TESTING, Arrays.asList(
                "test", "junit", "coverage", "assert"
        ));

        rules.put(CommentCategory.DOCUMENTATION, Arrays.asList(
                "comment", "javadoc", "documentation", "explain"
        ));

        rules.put(CommentCategory.POSITIVE, Arrays.asList(
                "good", "great", "nice", "excellent", "well done"
        ));
    }

    public static CommentCategory classify(String comment){

        String text = comment.toLowerCase();

        for(Map.Entry<CommentCategory, List<String>> entry : rules.entrySet()){

            List<String> keywords = entry.getValue();

            for(String keyword : keywords){
                if(text.contains(keyword)){
                    return entry.getKey();
                }
            }
        }

        return CommentCategory.UNKNOWN;
    }
}
