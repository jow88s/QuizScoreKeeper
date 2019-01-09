/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package models;

import javax.persistence.Entity;
import play.db.jpa.Model;


/**
 *
 * @author laptop
 */

@Entity
public class QuizTeam extends Model {
    
    
    public String mName = "";
    public int mScore = 0;
}
