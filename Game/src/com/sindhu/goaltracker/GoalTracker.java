package com.sindhu.goaltracker;

public interface GoalTracker {

    String SCORE = "SCORE";
    String LOSS = "LOSS";
    /**
     * Adds to the score of the team passed
     * @param name of the team
     */
    void scoreForTeam(String name);

    /**
     * Reduces the score of the team passed
     * @param name of the team
     */
    void lossForTeam(String name);

    /**
     * @return a string that contains all teams and their respective scores
     */
    String displayScores();
}

