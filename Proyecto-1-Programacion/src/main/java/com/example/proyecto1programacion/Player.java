package com.example.proyecto1programacion;

public class Player {




    private String player, date, durationTime,totalMovements;

    public Player(String player, String date, String durationTime, String totalMovements) {
        this.player = player;
        this.date = date;
        this.durationTime = durationTime;
        this.totalMovements = totalMovements;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getTotalMovements() {
        return totalMovements;
    }

    public void setTotalMovements(String totalMovements) {
        this.totalMovements = totalMovements;
    }
}
