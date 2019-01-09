
function checkEvents() {

    var url = "jsoncontroller/eventsResponse";

    $.post(url, {}, function (response) {

        //console.log("response=" + JSON.stringify(response));
        if (response.result === "ok") {

            if (response.restartTimer) {
                runCountDownTimer();
            }

            if (response.reloadTeamsTable) {
                reloadTeamsTable(response.jsonArrayQuizTeamsString);
                g_teamNameFadeInMilliSeconds = response.rankingTableFadeInInterval;
                console.log("g_teamNameFadeInMilliSeconds="+g_teamNameFadeInMilliSeconds);
            }
        }

    });

}

function reloadTeamsTable(jsonArrayQuizTeamsString) {

    $(".table-row-teams").each(function (index, obj) {
        var jqueryElement = $(obj);
        jqueryElement.remove();
    });
    var headerRow = $("#id-table-quiz-teams-header-row");

    var jsonArrayQuizTeams = JSON.parse(jsonArrayQuizTeamsString);

    $.each(jsonArrayQuizTeams, function (i) {
        var jsonObjectQuizTeam = this;

        // index must be from back to forward otherwise the number(=ranking) is incorrect
        var number = jsonArrayQuizTeams.length - i;

        insertTrElement(number, jsonObjectQuizTeam, headerRow);

    });


    myFadeIn($(".row-" + g_amountTeams));


}

function myFadeIn(element) {

    console.log("myFadeIn");

    var thisRowNumber = element.data("row-number");
    console.log("thisRowNumber=" + thisRowNumber);

    var nextRowNumber = thisRowNumber - 1;
    element.fadeIn(g_teamNameFadeInMilliSeconds, function () {

        if (thisRowNumber > 1) {
            var nextElement = $(".row-" + nextRowNumber);
            myFadeIn(nextElement);
        }
    });

}

function insertTrElement(number, jsonObjectQuizTeam, headerRow) {

    console.log("insertTrElement number=" + number + " jsonObjectQuizTeam=" + JSON.stringify(jsonObjectQuizTeam));

    if (jsonObjectQuizTeam) {

        var classRowNumber = "row-" + number;
        var trElement = $("<tr>", {
            class: 'table-row-teams ' + classRowNumber,
            style: 'display:none;',
            'data-row-number': number
        });
        var tdElementNumber = $("<td>", {text: number});
        var tdElementName = $("<td>", {text: jsonObjectQuizTeam.name});
        var tdElementScore = $("<td>", {text: jsonObjectQuizTeam.score});

        trElement.append(tdElementNumber);
        trElement.append(tdElementName);
        trElement.append(tdElementScore);

        headerRow.after(trElement);

    }
}
function runCountDownTimer() {

    if (g_countDownIntervalId !== 0)
        return; // there is already a count-down-timer running

    console.log("runCountDownTimer()");
    let countDownCounter = g_countDownTimerMax;

    g_countDownIntervalId = setInterval(function () {

        console.log("runCountDownTimer() countDownCounter=" + countDownCounter);

        if (countDownCounter >= 0) {

            $("#id-count-down-timer").text(countDownCounter);
            countDownCounter--;
        } else {
            clearInterval(g_countDownIntervalId);
            g_countDownIntervalId = 0;
            blinkingElement($("#id-count-down-timer"));
            playSound();
        }
    }, 1000);

}

function playSound() {
    var sound = document.getElementById("id-buzzer-audio");
    sound.play();
}

function blinkingElement(element) {

    for (var i = 0; i < 10; i++) {
        element.parent().fadeOut().fadeIn();
    }

}

$(document).on("click", "#id-button-start-count-down-timer", function (e) {

    runCountDownTimer();
});
