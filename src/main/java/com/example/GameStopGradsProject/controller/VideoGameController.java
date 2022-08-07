package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.service.VideoGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
//@Validated
@RequestMapping("/videogames")
public class VideoGameController {

    private VideoGameService videoGameService;

    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @PostMapping
    public ResponseEntity<VideoGame> create(
            //@Valid
            @RequestBody VideoGame videoGame) {

        VideoGame createdVideoGame = videoGameService.create(videoGame);

        return ResponseEntity
                .created(URI.create("/videogames" + ""))
                .body(createdVideoGame);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VideoGame>> findVideoGameById(
            @PathVariable Long id) {

        try {
            Optional<VideoGame> selectedVideoGame = videoGameService.findVideoGameById(id);

            return ResponseEntity
                    .created(URI.create("/videogames" + "/id"))
                    .body(selectedVideoGame);
        } catch(IdDoesNotExist x) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
