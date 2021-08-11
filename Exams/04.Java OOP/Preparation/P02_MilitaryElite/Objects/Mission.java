package ExamPreparation.P02_MilitaryElite.Objects;

import ExamPreparation.P02_MilitaryElite.Enums.State;

import java.util.Arrays;

public class Mission {
    private String codeName;
    private String missionState;

    public Mission(String codeName, String missionState) {
        this.codeName = codeName;
        setMissionState(missionState);
    }

    private void setMissionState(String missionState) {
        if (Arrays.stream(State.values()).anyMatch(s -> s.name().equals(missionState.toUpperCase()))) {
            if (missionState.equals("finished") || missionState.equals("inProgress")) {
                this.missionState = missionState;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void completeMission() {
        setMissionState(State.FINISHED.name().toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", this.codeName, this.missionState);
    }
}
