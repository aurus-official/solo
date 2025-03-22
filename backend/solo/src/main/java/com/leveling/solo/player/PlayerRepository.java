package com.leveling.solo.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerModel, Long> {
    @Query("SELECT ptable FROM player_table ptable WHERE ptable.username=?1")
    Optional<PlayerModel> getPlayerByUsername(String username);

}
