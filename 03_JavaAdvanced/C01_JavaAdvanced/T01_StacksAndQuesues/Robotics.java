package T01_StacksAndQuesues.Exercise;

import java.util.*;

public class Robotics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(";");
        Map<String, Integer> robots = new LinkedHashMap<>();

        for (String token : tokens) {
            String[] result = token.split("-");
            String name = result[0];
            int processingTime = Integer.parseInt(result[1]);
            robots.put(name, processingTime);
        }
        String[] startingHour = scanner.nextLine().split(":");
        int hours = Integer.parseInt(startingHour[0]);
        int minutes = Integer.parseInt(startingHour[1]);
        int seconds = Integer.parseInt(startingHour[2]);
        int startingSeconds = hours * 3600 + minutes * 60 + seconds;

        String input = scanner.nextLine();
        int secondsCounter = 0;
        ArrayDeque<String> queue = new ArrayDeque<>();

        while (!"End".equals(input)) {
            queue.offer(input);
            input = scanner.nextLine();
        }

        List<Integer> workingTimes = new ArrayList<>(robots.values());
        boolean busy = true;
        int index = -1;

        while (!queue.isEmpty()) {
            boolean grab = false;
            secondsCounter++;
            int currentTime = startingSeconds + secondsCounter;
            for (Map.Entry<String, Integer> robot : robots.entrySet()) {
                index++;
                if (currentTime >= robot.getValue()) {
                    int finishTime = currentTime + workingTimes.get(index);
                    robot.setValue(finishTime);
                    robotNameDetailStartingHour(robot.getKey(), queue.peek(), currentTime);
                    queue.poll();
                    busy = true;
                    grab = true;
                    break;
                }
            }
            index = -1;
            for (Map.Entry<String, Integer> robot : robots.entrySet()) {
                if (currentTime < robot.getValue()) {
                    busy = true;
                } else {
                    busy = false;
                    break;
                }
            }
            if (busy && queue.size() != 0 && !grab) {
                queue.offer(queue.poll());
            }
        }
    }

    private static void robotNameDetailStartingHour(String robotName, String detail, int startingTime) {
        long hours = startingTime / 3600;
        long reminder = startingTime - hours * 3600;
        long minutes = reminder / 60;
        reminder -= minutes * 60;
        long seconds = reminder;

        String output = String.format("%s - %s [%02d:%02d:%02d]", robotName, detail, hours, minutes, seconds);
        System.out.println(output);
    }
}
