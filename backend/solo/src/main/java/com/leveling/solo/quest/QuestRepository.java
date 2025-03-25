package com.leveling.solo.quest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends CrudRepository<QuestModel, Long> {

    // @Query("SELECT qtable FROM quest_table qtable INNER JOIN qtable.playerModel
    // ON qtable.playerModel=?1")
    // @Query("SELECT qtable FROM quest_table qtable INNER JOIN qtable.playerModel
    // ON qtable.playerModel=?1")

    // THIS ONE BELOW WORKS
    // @Query("SELECT qtable FROM quest_table qtable INNER JOIN qtable.playerModel
    // ON qtable.playerModel=?1")
    // Optional<List<QuestModel>> getAllQuestModelByPlayerId(PlayerModel
    // playerModel);

    @Query("SELECT qtable FROM quest_table qtable INNER JOIN qtable.playerModel ON qtable.playerModel.id=?1")
    Optional<List<QuestModel>> getAllQuestModelByPlayerId(Long id);

    // @Query("SELECT qtable FROM quest_table qtable WHERE
    // qtable.quest_table_fk=?1")
}
