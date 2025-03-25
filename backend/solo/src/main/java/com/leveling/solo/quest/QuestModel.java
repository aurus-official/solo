package com.leveling.solo.quest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.leveling.solo.player.PlayerModel;

@Entity(name = "quest_table")
@Table(name = "quest_table")
public class QuestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quest_id")
    private Long id;

    @Column(name = "quest_type")
    private QuestType questType;

    @Column(name = "quest_title")
    private String questTitle;

    @JoinColumn(name = "player_model_fk")
    @ManyToOne
    private PlayerModel playerModel;

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuestModel)) {
            return false;
        }
        return id != null && id.equals(((QuestModel) obj).getId());
    }

    public Long getId() {
        return id;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

}
