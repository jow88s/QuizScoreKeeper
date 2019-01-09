# QuizScoreKeeper
Colllect and show teamscores on a nice website

- You can enter as many teams as you want for tracking their score (eq. during a quiz)
- The 'administrator' has his own page for incrementing the scores for each round and has also a count-down timer. Their will be a sound played when the count-down timer hits zero
- When the scores are displayed then a little animation is done that will show every teamname as fade-in starting from lowest score to highest score

# Requirements
[Java Playframework](https://www.playframework.com) (only tested with [Play-1.5.x](https://downloads.typesafe.com/play/1.5.2/play-1.5.2.zip))

## Usage
- clone the repository
- install Java Playframework (version [Play-1.5.x](https://downloads.typesafe.com/play/1.5.2/play-1.5.2.zip))
- open a cmd and execute the command 'play run'
  - now, you can browse to http://localhost:9000 for the index.html that displays the scores of all the teams
  - for administration, you can browse to http://localhost:9000/application/admin for entering scores for each team and starting a count-down timer

## Customization
- Customizations are only 'hard-coded' possible
  - You can change the number of teams in the file 'index.html' via the variable 'g_amountTeams'
  - You can change the amount of seconds the count-down timer will start in the file 'index.html' via the variable 'g_countDownTimerMax'

#### License MIT

#### Author : jow88s@gmail.com

