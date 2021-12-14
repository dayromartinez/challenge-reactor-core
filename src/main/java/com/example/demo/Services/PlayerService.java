package com.example.demo.Services;

import com.example.demo.Models.PlayerDb;
import com.example.demo.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Flux<PlayerDb> getPlayers(){

        Flux<PlayerDb> players = playerRepository.findAll()
                .buffer(100)
                .flatMap(player -> Flux.fromStream(player.parallelStream()));
        return players;

    }

    public Flux<PlayerDb> getPlayersOlderThan34(){

        Flux<PlayerDb> playersOlderThan34 = playerRepository.findAll()
                .buffer(100)
                .flatMap(player -> Flux.fromStream(player.parallelStream()))
                .filter(jugador -> jugador.getAge() > 34);
        return playersOlderThan34;

    }

    public Flux<PlayerDb> getPlayersByClub(String club){

        Flux<PlayerDb> playersByClub = playerRepository.findAll()
                .buffer(100)
                .flatMap(player -> Flux.fromStream(player.parallelStream()))
                .filter(playerNoNullClub -> Objects.nonNull(playerNoNullClub.getClub()))
                .filter(jugador -> jugador.getClub().equals(club));

        return playersByClub;

    }
}
