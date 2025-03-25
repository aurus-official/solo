package com.leveling.solo.quest;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import com.leveling.solo.custom_exception.PlayerNotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/v1/quests")
@RestController
public class QuestController {

    private final QuestService questService;

    @Autowired
    QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping(path = "/{player_name}")
    ResponseEntity<List<QuestDTO>> getAllQuest(@PathVariable("player_name") String player_name) {
        List<QuestDTO> allQuestModel = new ArrayList<>();
        try {
            questService.getAllQuestModel(player_name, allQuestModel);
        } catch (PlayerNotExistException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(allQuestModel);
    }

    @PostMapping(path = "/{player_name}")
    ResponseEntity<String> addNewQuest(@PathVariable("player_name") String player_name,
            @Valid @RequestBody QuestDTO questDTO) {
        try {
            questService.addQuestModel("russel", questDTO);
        } catch (PlayerNotExistException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("It probably succeed...");
    }
}
