package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import managers.QuizTeamManager;

import models.*;

public class Application extends Controller {

    public static void index() {
        
        renderArgs.put("teams", QuizTeamManager.getAllQuizTeamsOrderByScore("desc"));
        
        render();
    }        
    
    public static void admin() {
        
        renderArgs.put("teams", QuizTeamManager.getAllQuizOrderByName());
        render();
    }
}