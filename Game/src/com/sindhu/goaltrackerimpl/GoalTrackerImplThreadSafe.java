package com.sindhu.goaltrackerimpl;

import java.util.HashMap;
import java.util.Map;

import com.sindhu.goaltracker.GoalTracker;

public class GoalTrackerImplThreadSafe implements GoalTracker {

    private Map<String, Integer> board = new HashMap<>();
    private String scoringTeam;
    private String lastMove;

    public GoalTrackerImplThreadSafe() {
    }

    public static void main(String[] args) {
        GoalTrackerImplThreadSafe obj = new GoalTrackerImplThreadSafe();
        obj.generateScoreBoard(3);

        // sequence of scores from given example
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
        synchronized (this) {
            if (scoringTeam != null && scoringTeam.equals(name) && lastMove.equals(SCORE))
                board.put(name, board.get(name) + 2);
            else
                board.put(name, board.get(name) + 1);

            lastMove = SCORE;
            scoringTeam = name;
        }
    }

    @Override
    public void lossForTeam(String name) {
        synchronized (this) {
            if (board.get(name) > 0)
                board.put(name, board.get(name) + -1);

            lastMove = LOSS;
        }
    }

    @Override
    public String displayScores() {
        String teamScore = "";
        boolean first = true;

        for (Map.Entry<String, Integer> score : board.entrySet()) {
            String currentTeamScore = "";
            currentTeamScore = currentTeamScore.concat(score.getKey()).concat(" : ")
                    .concat(score.getValue().toString());
            if (first) {
                teamScore = teamScore.concat(currentTeamScore);
                first = false;
            } else
                teamScore = teamScore.concat(", ").concat(currentTeamScore);
        }
        return teamScore;
    }

}
