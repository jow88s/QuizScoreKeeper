$(document).on("click", "#id-button-restart-count-down-timer", function (e) {

    var url = "/jsoncontroller/restarttimer";
    $.post(url, {}, function (response) {

        //console.log("response=" + JSON.stringify(response));

    });
});

function notifyIndexPage() {
    
     var url = "/jsoncontroller/reloadTeamTable";
    $.post(url, {}, function (response) {

        //console.log("response=" + JSON.stringify(response));

    });
    
}

function updateScores(jsonArrayQuizTeams) {
    
    var rankingTableFadeInInterval = $("#id-input-ranking-table-fade-in-interval").val() * 1000;
    
    if (rankingTableFadeInInterval) {        
    } else {
        rankingTableFadeInInterval = 5000;
    }
        
    var url = "/jsoncontroller/updateScores";
    $.post(url, {
        rankingTableFadeInInterval : rankingTableFadeInInterval,
        jsonArrayQuizTeamsString : JSON.stringify(jsonArrayQuizTeams)
    }, function (response) {

        //console.log("response=" + JSON.stringify(response));

    });
    
}

$(document).on("click","#id-button-apply-tmp-score", function(e){
    
    
    var quizTeams = [];
    
    // iterate every row
    $(".table-row-team").each(function(index,obj){
        
        var jqueryElement = $(obj);
        var teamNameElement = jqueryElement.find(".team-name");
        var teamName = teamNameElement.text();
        //console.log("teamName="+teamNameElement.text());
        
        var tmpScoreElement = jqueryElement.find(".tmp-score");
        var tmpScore = tmpScoreElement.val();
        var totalScoreElement = jqueryElement.find(".total-score");
        var totalScore = totalScoreElement.val();
        
        var newTotalScore = parseInt(tmpScore) + parseInt(totalScore);
        totalScoreElement.val(newTotalScore);
        tmpScoreElement.val(0);
        
        var jsonObjectQuizTeam = {};
        jsonObjectQuizTeam.name = teamName;
        jsonObjectQuizTeam.score = newTotalScore;
        
        quizTeams.push(jsonObjectQuizTeam);
        
    });
    
    updateScores(quizTeams);
});