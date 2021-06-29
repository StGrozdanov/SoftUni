package T01_WorkingWithAbstraction.Lab.P04_HotelReservation;

public enum Season {
    AUTUMN(1),
    SPRING(2),
    WINTER(3),
    SUMMER(4);

    private int seasonalValue;
    Season(int seasonalValue){
        this.seasonalValue = seasonalValue;
    }

    public int getSeasonalValue() {
        return seasonalValue;
    }
}
