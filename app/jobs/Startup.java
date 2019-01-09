/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import java.util.List;
import managers.QuizTeamManager;
import models.QuizTeam;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 *
 * @author laptop
 */

@OnApplicationStart
public class Startup extends Job {
    
    public void doJob() {
                
        if (false) {
            
            // remove all teams
            List<QuizTeam> allTeams = QuizTeamManager.getAllQuizTeams();
            for (QuizTeam quizTeam : allTeams) {
                quizTeam.delete();
            }
        }
        
        if (true) {
			// inserting quiz-teams
			QuizTeamManager.insertQuizTeam("Team1", 0);
			QuizTeamManager.insertQuizTeam("Team2", 0);
			QuizTeamManager.insertQuizTeam("Team3", 0);
			QuizTeamManager.insertQuizTeam("Team4", 0);
			QuizTeamManager.insertQuizTeam("Team5", 0);
			QuizTeamManager.insertQuizTeam("Team6", 0);        
			QuizTeamManager.insertQuizTeam("Team7", 0);        
			QuizTeamManager.insertQuizTeam("Team8", 0);        
			QuizTeamManager.insertQuizTeam("Team9", 0);        
			QuizTeamManager.insertQuizTeam("Team10", 0);   
        
        }
        
        
        
    }
    
}
