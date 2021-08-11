package ExamPreparation.P01_FootballTeamGenerator;

public class Player {

    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validator.nameValidator(name);
        this.name = name;
    }

    private void setEndurance(int endurance) {
        Validator.statValidator(endurance, "Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        Validator.statValidator(sprint, "Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        Validator.statValidator(dribble, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        Validator.statValidator(passing, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        Validator.statValidator(shooting, "Shooting");
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        return 1.0 * (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5;
    }
}
