package com.reviewinsight.core.cli;

import java.util.Scanner;

import com.reviewinsight.core.analytics.AIAnalyzerService;
import com.reviewinsight.core.analytics.DeveloperScoreService;
import com.reviewinsight.core.service.DataInitializer;

public class DashboardCLI {
	public static void start(){

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n====== REVIEWINSIGHT AI ENGINE ======");
            System.out.println("1. Insert Sample Data");
            System.out.println("2. Run AI Comment Analysis");
            System.out.println("3. Generate Developer Report");
            System.out.println("4. Exit");
            System.out.print("Select option: ");

            int ch = sc.nextInt();

            switch(ch){

                case 1:
                    DataInitializer.insertSampleData();
                    break;

                case 2:
                    AIAnalyzerService.analyzeAllComments();
                    break;

                case 3:
                    DeveloperScoreService.generateDeveloperReport();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
