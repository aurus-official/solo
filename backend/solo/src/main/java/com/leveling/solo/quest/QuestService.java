package com.leveling.solo.quest;

import java.util.List;
import java.util.Optional;

import com.leveling.solo.custom_exception.PlayerNotExistException;
import com.leveling.solo.player.PlayerModel;
import com.leveling.solo.player.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestService {

    private final QuestRepository questRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    QuestService(QuestRepository questRepository, PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.questRepository = questRepository;
    }

    void getAllQuestModel(String player_username, List<QuestDTO> allQuestModels) throws PlayerNotExistException {
        Optional<PlayerModel> playerModel = validatePlayerExists(player_username);

        Optional<List<QuestModel>> allMatchedQuestModels = questRepository
                .getAllQuestModelByPlayerId(playerModel.get().getId());

        allMatchedQuestModels.ifPresent(questModels -> questModels.forEach(questModel -> {
            QuestDTO questDTO = new QuestDTO();
            questDTO.setQuestType(questModel.getQuestType());
            questDTO.setQuestTitle(questModel.getQuestTitle());
            allQuestModels.add(questDTO);
        }));
    }

    void addQuestModel(String player_username, QuestDTO questDTO) throws PlayerNotExistException {
        Optional<PlayerModel> playerModel = validatePlayerExists(player_username);

        QuestModel questModel = new QuestModel();
        questModel.setQuestType(questDTO.getQuestType());
        questModel.setQuestTitle(questDTO.getQuestTitle());

        playerModel.get().addQuest(questModel);
    }

    private Optional<PlayerModel> validatePlayerExists(String player_username) throws PlayerNotExistException {
        Optional<PlayerModel> playerModel = playerRepository.getPlayerByUsername(player_username);

        if (playerModel.isEmpty()) {

            throw new PlayerNotExistException("Player does not exists.");
        }

        return playerModel;

    }
}
