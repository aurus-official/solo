package com.leveling.solo.quest;

import jakarta.validation.constraints.NotNull;

public class QuestDTO {
    @NotNull(message = "Quest type is required!")
    private QuestType questType;

    @NotNull(message = "Quest title is required!")
    private String questTitle;

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
