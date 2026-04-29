package me.ivan.RedLight.arena;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter @Setter
public class Arena {
    // config
    // Use worldguard regions.
    private String deathZone;
    private String safeZone;

    private boolean rewardEnabled;
    private double rewardMoney;

    private int rounds;
    private int maxPlayers;

    // locations
    private String waitWorld;
    private double waitX;
    private double waitY;
    private double waitZ;

    private String lobbyWorld;
    private double lobbyX;
    private double lobbyY;
    private double lobbyZ;

    // active variables
    private ArenaState state;
    private ArrayList<UUID> players;
    private int currentRound = 0;

}
