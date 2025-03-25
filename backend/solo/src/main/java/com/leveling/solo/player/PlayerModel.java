package com.leveling.solo.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.leveling.solo.quest.QuestModel;

@Entity(name = "player_table")
@Table(name = "player_table")
public class PlayerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_username")
    private String username;

    @Column(name = "player_password")
    private String password;

    @Column(name = "quest_table_fk")
    @OneToMany(mappedBy = "playerModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestModel> quest_table_list = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addQuest(QuestModel questModel) {
        quest_table_list.add(questModel);
        questModel.setPlayerModel(this);
    }

    public void removeQuest(QuestModel questModel) {
        quest_table_list.remove(questModel);
        questModel.setPlayerModel(null);

    }
}
