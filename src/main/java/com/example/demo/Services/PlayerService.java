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

    public Flux<PlayerDb> getPlayersOlderThan34ByClub(String club){
        System.out.println(club);
        Flux<PlayerDb> playersOlderThan34ByClub = playerRepository.findAll()
                .buffer(100)
                .flatMap(player -> Flux.fromStream(player.parallelStream()))
                .filter(playerNoNullClub -> Objects.nonNull(playerNoNullClub.getClub()))
                .filter(jugador -> jugador.getAge() > 34  && jugador.getClub().equals(club));

        return playersOlderThan34ByClub;

    }
}
