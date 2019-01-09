/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Tool.Globals;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import managers.QuizTeamManager;
import play.Logger;
import play.mvc.Controller;

/**
 *
 * @author laptop
 */
public class JsonController extends Controller {

    public static void updateScores(String rankingTableFadeInInterval,String jsonArrayQuizTeamsString) {
        
        Logger.debug("JsonController.updateScores rankingTableFadeInInterval="+rankingTableFadeInInterval);

        try {
        Globals.rankingTableFadeInInterval = Integer.parseInt(rankingTableFadeInInterval);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonArrayQuizTeamsString);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            String teamName = jsonObject.get("name").getAsString();
            int score = jsonObject.get("score").getAsInt();

            QuizTeamManager.insertQuizTeam(teamName, score);

        }
        
        reloadTeamsTable();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "ok");

        renderJSON(jsonObject);

    }

    public static void reloadTeamsTable() {

        Globals.reloadTeamsTable = true;
        
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "ok");
        

        renderJSON(jsonObject);
    }

    public static void restartTimer() {

        Globals.restartTimer = true;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "ok");

        renderJSON(jsonObject);
    }

    public static void eventsResponse() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "ok");
        jsonObject.addProperty("restartTimer", Globals.restartTimer);
        jsonObject.addProperty("reloadTeamsTable", Globals.reloadTeamsTable);
        

        if (Globals.restartTimer)
            Globals.restartTimer = false;
        
        if (Globals.reloadTeamsTable) {
        
            jsonObject.addProperty("rankingTableFadeInInterval", Globals.rankingTableFadeInInterval);
            jsonObject.addProperty("jsonArrayQuizTeamsString", QuizTeamManager.getAllQuizTeamsAsJsonArrayString());       
            Globals.reloadTeamsTable = false;
        }
        
        renderJSON(jsonObject);

    }

}
