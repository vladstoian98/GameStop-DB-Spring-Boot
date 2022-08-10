package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.service.VideoGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/videogames")
@AllArgsConstructor
public class VideoGameController {

    private final VideoGameService videoGameService;

    @PostMapping
    public ResponseEntity<VideoGame> create(
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

    @DeleteMapping("/deletion/{id}")
    public ResponseEntity<?> deleteVideoGameById(@PathVariable Long id) {
        try {
            videoGameService.deleteVideoGameById(id);

            return ResponseEntity
                    .ok()
                    .build();
        }
        catch (IdDoesNotExist e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("/{videoGameId}/{gameCharacterId}")
    public void assignBothWays(
            @PathVariable long videoGameId,
            @PathVariable long gameCharacterId) {
        videoGameService.assignBothWays(videoGameId, gameCharacterId);
    }
}
