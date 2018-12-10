package com.sindhu.goaltrackerimpl;

import java.util.HashMap;
import java.util.Map;

import com.sindhu.goaltracker.GoalTracker;

public class GoalTrackerImpl implements GoalTracker {

    private Map<String, Integer> board;
    private String ScoringTeam;
    private String lastMove;

    public GoalTrackerImpl() {
        board = new HashMap<>();
        ScoringTeam = "";
        lastMove = "";
    }

    public static void main(String[] args) {
        GoalTrackerImpl obj = new GoalTrackerImpl();
        obj.generateScoreBoard(3);
        
        //sequence of scores from given example
        obj.scoreForTeam("A");
        obj.scoreForTeam("A");
        obj.scoreForTeam("B");
        obj.scoreForTeam("A");
        obj.scoreForTeam("C");
        obj.scoreForTeam("C");
        obj.lossForTeam("C");
        obj.lossForTeam("B");
        obj.scoreForTeam("C");
        obj.scoreForTeam("C");
        obj.scoreForTeam("C");
        
     // score should not decrement below 0
        obj.lossForTeam("B");

        System.out.println(obj.displayScores());
    }

    // will receive 'N'(number of teams) and generate a score board accordingly
    public void generateScoreBoard(int N) {
        char startwith = 'A';
        board.put(Character.toString(startwith), 0);

        for (int i = 1; i < N; i++) {
            board.put(Character.toString(++startwith), 0);
        }
    }

    @Override
    public void scoreForTeam(String name) {
        if (ScoringTeam.equals(name) && lastMove.equals("Score"))
            board.put(name, board.get(name) + 2);
        else
            board.put(name, board.get(name) + 1);

        lastMove = "Score";
        ScoringTeam = name;
    }

    @Override
    public void lossForTeam(String name) {
        if (board.get(name) > 0)
            board.put(name, board.get(name) + -1);
        
        lastMove = "Loss";
    }

    @Override
    public String displayScores() {
        String teamScore = "";
        boolean first = true;

        for (Map.Entry<String, Integer> score : board.entrySet()) {
            String currentTeamScore = "";
            currentTeamScore = currentTeamScore.concat(score.getKey()).concat(" : ")
                    .concat(score.getValue().toString());
            if (!first)
                teamScore = teamScore.concat(", ").concat(currentTeamScore);
            else
                teamScore = teamScore.concat(currentTeamScore);

            first = false;
        }

        return teamScore;
    }

}
