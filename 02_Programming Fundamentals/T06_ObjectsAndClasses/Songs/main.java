package ObjectsAndClasses09.Songs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfSongs = Integer.parseInt(scanner.nextLine());

        List<Songs> songs = new ArrayList<>();

        for (int i = 0; i < numberOfSongs; i++) {
            String[] data = scanner.nextLine().split("_");

            String type = data[0];
            String name = data[1];
            String time = data[2];

            Songs song = new Songs();

            song.setTypeList(type);
            song.setName(name);
            song.setTime(time);

            songs.add(song);

        }

        String typeList = scanner.nextLine();

        if ("all".equals(typeList)) {
            for (Songs sound : songs) {
                System.out.println(sound.getName());
            }
        } else {
            for (Songs sound : songs) {
                if (sound.getTypeList().equals(typeList)) {
                    System.out.println(sound.getName());
                }
            }
        }
    }
}
