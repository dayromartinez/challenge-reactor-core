package com.example.demo.Controller;


import com.example.demo.Models.PlayerDb;
import com.example.demo.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public Flux<PlayerDb> getPlayers(){
        return playerService.getPlayers();
    }

    @GetMapping(value = "/players/filterByAge")
    public Flux<PlayerDb> getPlayersOlderThan34(){
        return playerService.getPlayersOlderThan34();
    }

}
