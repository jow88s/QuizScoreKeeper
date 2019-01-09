/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import models.QuizTeam;

/**
 *
 * @author laptop
 */
public class QuizTeamManager {

    public static void insertQuizTeam(String teamName, int score) {

        QuizTeam quizTeam = QuizTeam.find("mName=:n").bind("n", teamName).first();
        if (quizTeam == null) {
            quizTeam = new QuizTeam();
            quizTeam.mName = teamName;
			quizTeam.mScore = score;
        } else {
			// team already present in database, so check if the score is strict positive to avoid that the score is set to 0 during startup
			if (score > 0) {
				quizTeam.mScore = score;
			}
		}

        quizTeam.save();

    }

    // sort by score from high to low
    public static String getAllQuizTeamsAsJsonArrayString() {

        JsonArray jsonArray = new JsonArray();

        List<QuizTeam> allTeams = getAllQuizTeamsOrderByScore("asc");
        //List<QuizTeam> allTeams = getAllQuizTeams();
        for (QuizTeam team : allTeams) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", team.mName);
            jsonObject.addProperty("score", team.mScore);

            jsonArray.add(jsonObject);

        }

        return jsonArray.toString();
    }

    public static List<QuizTeam> getAllQuizTeams() {

        List<QuizTeam> resultList = new ArrayList();

        resultList = QuizTeam.all().fetch();

        return resultList;
    }

    public static List<QuizTeam> getAllQuizTeamsOrderByScore(String descOrAsc) {
        
        if (descOrAsc == null || descOrAsc.isEmpty())
            descOrAsc = "asc";

        List<QuizTeam> resultList = new ArrayList();

        resultList = QuizTeam.find("order by mScore " + descOrAsc).fetch();

        return resultList;
    }

    public static List<QuizTeam> getAllQuizOrderByName() {

        List<QuizTeam> resultList = new ArrayList();

        resultList = QuizTeam.find("order by mName").fetch();

        return resultList;
    }
}
