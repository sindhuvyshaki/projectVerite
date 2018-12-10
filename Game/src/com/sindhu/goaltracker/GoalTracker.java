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
     * Display the final tally
     * @return
     */
    String displayScores();
}

