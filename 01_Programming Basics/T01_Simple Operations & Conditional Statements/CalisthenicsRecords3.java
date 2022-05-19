import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalisthenicsRecords3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Double> currentExercise;
        Map<String, Double> score = new LinkedHashMap<>();
        Map<String, Double> currentHL;
        Map<String, Double> exerciseHL = new LinkedHashMap<>();

        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            String exercise = tokens[0];
            String fullExerciseName = findFullName(exercise);
            currentExercise = findExerciseRepetitionsScore(exercise, tokens);
            currentHL = findExerciseHL(exercise, tokens);
            String name = currentHL.keySet().toString().replace("[", "")
                    .replace("]", "");
            exerciseHL.put(name,     currentHL.get(name));
            score.put(fullExerciseName, currentExercise.get(exercise));
            input = scanner.nextLine();
        }
        System.out.println("Exercise Scores:");
        score.forEach((key, value) -> System.out.printf("%s: %.2f points.%n", key, value));

        // DATABASE:
        List<Double> pushWeek = new ArrayList<>(Arrays.asList(26.15253333333333, 125.79498666666666, 64.62378666666666, 69.01466666666667, 83.89205333333334, 108.6328, 34.272, 79.968, 64.3008));
        List<Double> pullWeek = new ArrayList<>(Arrays.asList(26.15253333333333, 127.25056000000001, 64.90176000000001, 71.33418666666667, 83.18016, 110.17578666666668, 34.272, 78.82560000000001, 64.3008));

        List<Double> weekTotal = new ArrayList<>(Arrays.asList(73.37704296296297, 0.0, 0.0, 0.0));
        List<Double> monthRecords = new ArrayList<>(Arrays.asList(72.3800362962963, 62.75, 62.724692888888896));
        List<Double> records = new ArrayList<>(Arrays.asList(24.670613333333336, 16.464266666666667, 19.381439999999998, 25.132800000000003, 15.475200000000001, 25.792, 13.708800000000002, 22.848, 25.792, 11.90933333333333, 19.05493333333333, 26.15253333333333, 59.4048, 20.563200000000002, 18.6048, 45.696, 43.77600000000001));

        // TOP 3 Months:
        // 1. August, 2021: 72.38 points
        // 2. July, 2021: 62.75 points
        // 3. June, 2021: 62.73 points

        // TOP 3 Weeks:
        // 1. September, 2021, Week 1 - 73.38 points
        // 2. August, 2021, Week 4 - 72.96 points
        // 3. August, 2021, Week 2 - 72.79 points


        System.out.println();
        System.out.println("Records at exercises:");
        findRecords(score, records);
        System.out.println();
        System.out.println("Exercise HL:");
        exerciseHL.forEach((key, value) -> System.out.printf("%s: %.0f repetitions.%n", key, value));
        System.out.println("Please, select week type between push / pull");
        String type = scanner.nextLine();
        Map<String, Double> muscleGroups = new LinkedHashMap<>();
        switch (type) {
            case "push":
                muscleGroups = findProgressByMuscleGroup(score, pushWeek);
                break;
            case "pull":
                muscleGroups = findProgressByMuscleGroup(score, pullWeek);
                break;
            default:
                break;
        }
        System.out.println("For week total: TOTAL WEEK; For month total: TOTAL MONTH");
        String totalType = scanner.nextLine();
        OptionalDouble week;
        OptionalDouble month;
        switch (totalType) {
            case "TOTAL WEEK":
                week = muscleGroups.values().stream().mapToDouble(Double::doubleValue).average();
                System.out.println(week.getAsDouble());
                break;
            case "TOTAL MONTH":
                week = muscleGroups.values().stream().mapToDouble(Double::doubleValue).average();
                weekTotal.add(week.getAsDouble());
                month = weekTotal.stream().mapToDouble(Double::doubleValue).average();
                System.out.printf("Last week total: %f%n", week.getAsDouble());
                System.out.println(month.getAsDouble());
                double currentMonth = month.getAsDouble();
                if (currentMonth > monthRecords.get(0)) {
                    System.out.println("This is the most successful Month in the history!");
                } else if (currentMonth > monthRecords.get(1)) {
                    System.out.println("This is the #2 Most Successful Month in history!");
                } else if (currentMonth > monthRecords.get(2)) {
                    System.out.println("This is the #3 Most Successful Month in history!");
                }
                break;
        }
    }

    private static List<Double> resultsByMuscleGroup(Map<String, Double> muscleGroups) {
        List<Double> newWeekResults = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        for (Map.Entry<String, Double> entry : muscleGroups.entrySet()) {
            switch (entry.getKey()) {
                case "Neck":
                    newWeekResults.set(0, entry.getValue());
                    break;
                case "Shoulders":
                    newWeekResults.set(1, entry.getValue());
                    break;
                case "Chest":
                    newWeekResults.set(2, entry.getValue());
                    break;
                case "Biceps":
                    newWeekResults.set(3, entry.getValue());
                    break;
                case "Triceps":
                    newWeekResults.set(4, entry.getValue());
                    break;
                case "Back":
                    newWeekResults.set(5, entry.getValue());
                    break;
                case "Abs":
                    newWeekResults.set(6, entry.getValue());
                    break;
                case "Glut":
                    newWeekResults.set(7, entry.getValue());
                    break;
                case "Legs":
                    newWeekResults.set(8, entry.getValue());
                    break;
            }
        }
        return newWeekResults;
    }

    private static Map<String, Double> findProgressByMuscleGroup(Map<String, Double> score, List<Double> pullWeek) {
        Map<String, Double> muscleGroups = new LinkedHashMap<>();
        Map<String, Map<Double, String>> result = new LinkedHashMap<>();

        for (Map.Entry<String, Double> entry : score.entrySet()) {
            String exercise = entry.getKey();
            double value = entry.getValue();
            double newValue;
            switch (exercise) {
                case "Dips":
                case "Push Ups":
                    muscleGroups.putIfAbsent("Chest", 0.0);
                    newValue = value + muscleGroups.get("Chest");
                    muscleGroups.put("Chest", newValue);
                    muscleGroups.putIfAbsent("Shoulders", 0.0);
                    newValue = value + muscleGroups.get("Shoulders");
                    muscleGroups.put("Shoulders", newValue);
                    muscleGroups.putIfAbsent("Triceps", 0.0);
                    newValue = value + muscleGroups.get("Triceps");
                    muscleGroups.put("Triceps", newValue);
                    break;
                case "Pull Ups":
                case "Rows":
                    muscleGroups.putIfAbsent("Biceps", 0.0);
                    newValue = value + muscleGroups.get("Biceps");
                    muscleGroups.put("Biceps", newValue);
                    muscleGroups.putIfAbsent("Back", 0.0);
                    newValue = value + muscleGroups.get("Back");
                    muscleGroups.put("Back", newValue);
                    break;
                case "Pike Press":
                    muscleGroups.putIfAbsent("Shoulders", 0.0);
                    newValue = value + muscleGroups.get("Shoulders");
                    muscleGroups.put("Shoulders", newValue);
                    muscleGroups.putIfAbsent("Triceps", 0.0);
                    newValue = value + muscleGroups.get("Triceps");
                    muscleGroups.put("Triceps", newValue);
                    break;
                case "Bicep Curls":
                case "Chin Ups":
                    muscleGroups.putIfAbsent("Biceps", 0.0);
                    newValue = value + muscleGroups.get("Biceps");
                    muscleGroups.put("Biceps", newValue);
                    break;
                case "Ring Layouts":
                    muscleGroups.putIfAbsent("Shoulders", 0.0);
                    newValue = value + muscleGroups.get("Shoulders");
                    muscleGroups.put("Shoulders", newValue);
                    muscleGroups.putIfAbsent("Triceps", 0.0);
                    newValue = value + muscleGroups.get("Triceps");
                    muscleGroups.put("Triceps", newValue);
                    muscleGroups.putIfAbsent("Back", 0.0);
                    newValue = value + muscleGroups.get("Back");
                    muscleGroups.put("Back", newValue);
                    muscleGroups.putIfAbsent("Abs", 0.0);
                    newValue = value + muscleGroups.get("Abs");
                    muscleGroups.put("Abs", newValue);
                    break;
                case "Face Pulls":
                    muscleGroups.putIfAbsent("Shoulders", 0.0);
                    newValue = value + muscleGroups.get("Shoulders");
                    muscleGroups.put("Shoulders", newValue);
                    muscleGroups.putIfAbsent("Back", 0.0);
                    newValue = value + muscleGroups.get("Back");
                    muscleGroups.put("Back", newValue);
                    break;
                case "Lever Raises":
                    muscleGroups.putIfAbsent("Back", 0.0);
                    newValue = value + muscleGroups.get("Back");
                    muscleGroups.put("Back", newValue);
                    muscleGroups.putIfAbsent("Abs", 0.0);
                    newValue = value + muscleGroups.get("Abs");
                    muscleGroups.put("Abs", newValue);
                    break;
                case "Iron Cross Pulls":
                    muscleGroups.putIfAbsent("Shoulders", 0.0);
                    newValue = value + muscleGroups.get("Shoulders");
                    muscleGroups.put("Shoulders", newValue);
                    muscleGroups.putIfAbsent("Back", 0.0);
                    newValue = value + muscleGroups.get("Back");
                    muscleGroups.put("Back", newValue);
                    muscleGroups.putIfAbsent("Chest", 0.0);
                    newValue = value + muscleGroups.get("Chest");
                    muscleGroups.put("Chest", newValue);
                    break;
                case "Neck":
                    muscleGroups.putIfAbsent("Neck", 0.0);
                    newValue = value + muscleGroups.get("Neck");
                    muscleGroups.put("Neck", newValue);
                    break;
                case "Glut Bridge":
                case "Reverse Lounge":
                    muscleGroups.putIfAbsent("Glut", 0.0);
                    newValue = value + muscleGroups.get("Glut");
                    muscleGroups.put("Glut", newValue);
                    break;
                case "Pistol Squads":
                case "Squads":
                case "Bulgarian Split Squad":
                    muscleGroups.putIfAbsent("Legs", 0.0);
                    newValue = value + muscleGroups.get("Legs");
                    muscleGroups.put("Legs", newValue);
                    break;
            }
        }
        for (Map.Entry<String, Double> entry : muscleGroups.entrySet()) {
            result.put(entry.getKey(), new LinkedHashMap<>());
        }
        List<Double> newWeek = resultsByMuscleGroup(muscleGroups);
        int currentIndex = 0;
        for (Double nWeek : newWeek) {
            String currentKey = "";
            switch (currentIndex) {
                case 0:
                    currentKey = "Neck";
                    break;
                case 1:
                    currentKey = "Shoulders";
                    break;
                case 2:
                    currentKey = "Chest";
                    break;
                case 3:
                    currentKey = "Biceps";
                    break;
                case 4:
                    currentKey = "Triceps";
                    break;
                case 5:
                    currentKey = "Back";
                    break;
                case 6:
                    currentKey = "Abs";
                    break;
                case 7:
                    currentKey = "Glut";
                    break;
                case 8:
                    currentKey = "Legs";
                    break;
            }
            double percentage;
            if (nWeek > pullWeek.get(currentIndex)) {
                percentage = Math.round(((nWeek / pullWeek.get(currentIndex)) - 1) * 100);
                result.putIfAbsent(currentKey, new LinkedHashMap<>());
                result.get(currentKey).put(muscleGroups.get(currentKey), String.format("+%.0f%% Progress", percentage));
            } else if (nWeek < pullWeek.get(currentIndex)) {
                percentage = Math.round(((pullWeek.get(currentIndex) / nWeek) - 1) * 100);
                result.putIfAbsent(currentKey, new LinkedHashMap<>());
                result.get(currentKey).put(muscleGroups.get(currentKey), String.format("-%.0f%% Regress", percentage));
            } else {
                result.putIfAbsent(currentKey, new LinkedHashMap<>());
                result.get(currentKey).put(muscleGroups.get(currentKey), "Neutral");
            }
            currentIndex++;
        }
        result.forEach((key, value) -> {
            for (Map.Entry<Double, String> entry : value.entrySet()) {
                System.out.printf("%s -> Load: %.2f | Condition: %s.%n", key, entry.getKey(), entry.getValue());
            }
        });
        System.out.println("The new weekly results are now:");
        System.out.println(newWeek);
        return muscleGroups;
    }

    private static void findRecords(Map<String, Double> score, List<Double> records) {
        List<Double> newWeek = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        List<String> exercises = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        int index = 0;
        for (Map.Entry<String, Double> entry : score.entrySet()) {
            switch (entry.getKey()) {
                case "Dips":
                    index = 0;
                    break;
                case "Pull Ups":
                    index = 1;
                    break;
                case "Rows":
                    index = 2;
                    break;
                case "Push Ups":
                    index = 3;
                    break;
                case "Pike Press":
                    index = 4;
                    break;
                case "Bicep Curls":
                    index = 5;
                    break;
                case "Chin Ups":
                    index = 6;
                    break;
                case "Ring Layouts":
                    index = 7;
                    break;
                case "Face Pulls":
                    index = 8;
                    break;
                case "Lever Raises":
                    index = 9;
                    break;
                case "Iron Cross Pulls":
                    index = 10;
                    break;
                case "Neck":
                    index = 11;
                    break;
                case "Glut Bridge":
                    index = 12;
                    break;
                case "Reverse Lounge":
                    index = 13;
                    break;
                case "Pistol Squads":
                    index = 14;
                    break;
                case "Squads":
                    index = 15;
                    break;
                case "Bulgarian Split Squads":
                    index = 16;
                    break;
            }
            double current = entry.getValue();
            newWeek.set(index, current);
            exercises.set(index, entry.getKey());
        }
        for (int i = 0; i < newWeek.size(); i++) {
            double nWeek = newWeek.get(i);
            double currentRecord = records.get(i);
            String exercise = exercises.get(i);
            if (exercise.equals("")){
                exercise = "Unrecorded Exercise";
            }
            String record;
            if (nWeek > currentRecord) {
                record = "*NEW RECORD*";
                records.set(i, nWeek);
            } else if (nWeek < currentRecord) {
                record = "Not a record";
            } else {
                record = "*RECORD*";
            }
            System.out.printf("%s: %s%n", exercise, record);
        }
        System.out.println("Records database now looks like:");
        System.out.println(records);
    }


    private static String findFullName(String exercise) {
        switch (exercise) {
            case "Iron":
                return "Iron Cross Pulls";
            case "Rolls":
                return "Rows";
            case "Chin":
                return "Chin Ups";
            case "Ring":
                return "Ring Layouts";
            case "Face":
                return "Face Pulls";
            case "Lever":
                return "Lever Raises";
            case "Pull":
                return "Pull Ups";
            case "Push":
                return "Push Ups";
            case "Pike":
                return "Pike Press";
            case "Bicep":
                return "Bicep Curls";
            case "Glut":
                return "Glut Bridge";
            case "Reverse":
                return "Reverse Lounge";
            case "Pistol":
                return "Pistol Squads";
            case "Bulgarian":
                return "Bulgarian Split Squads";
            default:
                return exercise;
        }
    }

    private static Map<String, Double> findExerciseRepetitionsScore(String exercise, String[] tokens) {
        String word = Arrays.toString(tokens).toLowerCase().replace(",", "").replace("[", "")
                .replace("]", "");
        Map<String, Map<String, List<Double>>> exerciseResult = new LinkedHashMap<>();
        List<Double> repetitions = findRepetitions(word);
        List<Integer> level = findLevel(word);
        List<Double> weight = findWeight(word);
        double score;
        String criteria = "\\*(?<exercise>[a-zA-Z\\s\\d]+)\\*";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        exerciseResult.put(exercise, new LinkedHashMap<>());
        while (matcher.find()) {
            String variation = matcher.group("exercise");
            exerciseResult.get(exercise).putIfAbsent(variation, new ArrayList<>());
            switch (exercise) {
                case "Dips":
                    switch (variation) {
                        case "weighted bulgarian dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "bulgarian dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted ring dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "regular ring dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted bar dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby ring dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "regular bar dips":
                            exerciseResult.get(exercise).get(variation).add
                                    (8.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Pull":
                    switch (variation) {
                        case "l sit weighted ring pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit weighted pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit ring pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "regular pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby ring pull ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (8.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Rolls":
                    switch (variation) {
                        case "weighted front lever rolls":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "front lever rolls":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "half front lever rolls":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby front lever rolls":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted one handed ring rolls":
                            score = 12.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "one handed ring rolls":
                            score = 10.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "archer ring rolls":
                            score = 8.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "ring rolls":
                            score = 6.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "regular rolls":
                            score = 4.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                    }
                    break;
                case "Push":
                    switch (variation) {
                        case "one handed ring push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (23.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "one handed push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (22.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby one handed push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (21.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted inverted archer push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted archer push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "inverted archer push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "archer push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted inverted ring push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted ring push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "inverted ring push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (8.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (6.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (4.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "regular push ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (2.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Pike":
                    switch (variation) {
                        case "weighted handstand pike press":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "handstand pike press":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted half handstand pike press":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "half handstand pike press":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "supported handstand pike press":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted elevated pike press":
                            score = 10.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "elevated pike press":
                            score = 8.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "regular pike press":
                            score = 6.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                    }
                    break;
                case "Bicep":
                    switch (variation) {
                        case "weighted one handed bicep curls":
                            score = 20.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "one handed bicep curls":
                            score = 18.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "weighted bicep curls":
                            score = 16.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "ring bicep curls":
                            score = 14.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                    }
                    break;
                case "Chin":
                    switch (variation) {
                        case "l sit weighted ring chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit weighted chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit ring chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "l sit chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "regular chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby ring chin ups":
                            exerciseResult.get(exercise).get(variation).add
                                    (8.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Ring":
                    switch (variation) {
                        case "weighted ring layouts":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "weighted ring layouts lvl 3":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring layouts":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring layouts lvl 3":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring layouts lvl 2":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "ring layouts lvl 1":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby ring layouts":
                            exerciseResult.get(exercise).get(variation).add
                                    (8.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Face":
                    switch (variation) {
                        case "weighted one handed face pulls":
                            score = 20.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "one handed face pulls":
                            score = 18.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "weighted face pulls":
                            score = 16.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                        case "face pulls":
                            score = 14.0 * ((1.0 * repetitions.get(0) / 1500) + 1);
                            if (level.get(0) == 3) {
                                score *= 0.95;
                            } else if (level.get(0) == 2) {
                                score *= 0.9;
                            } else if (level.get(0) == 1) {
                                score *= 0.85;
                            }
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            level.remove(0);
                            break;
                    }
                    break;
                case "Lever":
                    switch (variation) {
                        case "weighted front lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "front lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "semi-tucked lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "tucked lvl 2 lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "tucked lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "supported tucked lever raises":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Iron":
                    switch (variation) {
                        case "weighted iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "supported iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "supported grip iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "supported supported grip iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby supported grip iron cross pulls":
                            exerciseResult.get(exercise).get(variation).add
                                    (10.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Neck":
                case "Glut":
                case "Reverse":
                case "Squads":
                case "Bulgarian":
                    score = weight.get(0) * ((repetitions.get(0) / 1500) + 1);
                    exerciseResult.get(exercise).get(variation).add(score);
                    repetitions.remove(0);
                    weight.remove(0);
                    break;
                case "Pistol":
                    switch (variation) {
                        case "weighted pistol squads":
                            exerciseResult.get(exercise).get(variation).add
                                    (20.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "pistol squads":
                            exerciseResult.get(exercise).get(variation).add
                                    (18.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "pistol eccentric":
                            exerciseResult.get(exercise).get(variation).add
                                    (17.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "half way down pistol squads":
                            exerciseResult.get(exercise).get(variation).add
                                    (16.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "semi supported half way down pistol squads":
                            exerciseResult.get(exercise).get(variation).add
                                    (14.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                        case "baby pistol squads":
                            exerciseResult.get(exercise).get(variation).add
                                    (12.0 * ((1.0 * repetitions.get(0) / 1500) + 1));
                            repetitions.remove(0);
                            break;
                    }
                    break;
            }
        }
        Map<String, List<Double>> endResult = new LinkedHashMap<>();
        int reps = fullRepetitions(word);
        for (Map.Entry<String, Map<String, List<Double>>> outerMap : exerciseResult.entrySet()) {
            for (Map<String, List<Double>> value : exerciseResult.values()) {
                for (Map.Entry<String, List<Double>> entry : value.entrySet()) {
                    if (entry.getValue().size() > 1) {
                        double average = entry.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
                        endResult.get(outerMap.getKey()).add(average);
                    } else if (entry.getValue().size() == 1) {
                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
                        endResult.get(outerMap.getKey()).add(entry.getValue().get(0));
                    } else {
                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
                        endResult.get(outerMap.getKey()).add(0.0);
                    }
                }
            }
        }
        Map<String, Double> totalResult = new LinkedHashMap<>();
        if (endResult.size() > 0) {
            double factor1 = endResult.get(exercise).stream().mapToDouble(Double::doubleValue).average().getAsDouble();
            double totalScore = factor1 * ((1.0 * reps / 250) + 1);
            totalResult.put(exercise, totalScore);
        } else {
            totalResult.put(exercise, 0.0);
        }
        return totalResult;
    }

    private static List<Double> findWeight(String word) {
        List<Double> result = new ArrayList<>();
        String criteria = "\\d+\\s+kg";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            String[] tokens = matcher.group().split("\\s+");
            result.add(Double.parseDouble(tokens[0]));
        }
        if (result.size() > 1) {
            double sum = result.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
            result.clear();
            result.add(sum);
        } else if (result.size() == 1 && result.get(0) == 0) {
            result.set(0, 1.0);
        } else {
            result.add(1.0);
        }
        return result;
    }

    private static List<Integer> findLevel(String word) {
        List<Integer> result = new ArrayList<>();
        String criteria = "(LVL|lvl)\\s+\\d";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            String[] tokens = matcher.group().split("\\s+");
            result.add(Integer.parseInt(tokens[1]));
        }
        return result;
    }

    private static int fullRepetitions(String word) {
        List<Integer> result;
        String criteria = "[\\d]+[/]?(\\d+)?[/]?(\\d+)[/]?(\\d+)?$";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {
            result = Arrays.stream(matcher.group().split("/")).map(Integer::parseInt).collect(Collectors.toList());
            return result.stream().mapToInt(Integer::intValue).sum();
        }
        return 0;
    }

    private static List<Double> findRepetitions(String word) {
        List<Double> result = new ArrayList<>();
        String criteria = "[\\d]+[/]?(\\d+)?[/]?(\\d+)[/]?(\\d+)?$";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {
            result = Arrays.stream(matcher.group().split("/")).map(Double::parseDouble).collect(Collectors.toList());
        }
        if (result.size() == 0) {
            result.add(0.0);
        }
        return result;
    }

    private static Map<String, Double> findExerciseHL(String exercise, String[] tokens) {
        String word = Arrays.toString(tokens).toLowerCase().replace(",", "").replace("[", "")
                .replace("]", "");
        Map<String, Double> exerciseResult = new LinkedHashMap<>();
        List<Double> repetitions = findRepetitions(word);
        String criteria = "\\*(?<exercise>[a-zA-Z\\s\\d]+)\\*";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()) {
            String variation = matcher.group("exercise");
            switch (exercise) {
                case "Dips":
                    switch (variation) {
                        case "weighted bulgarian dips":
                        case "regular bar dips":
                        case "baby ring dips":
                        case "bulgarian dips":
                        case "weighted ring dips":
                        case "regular ring dips":
                        case "weighted bar dips":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Pull":
                    switch (variation) {
                        case "l sit weighted ring pull ups":
                        case "l sit weighted pull ups":
                        case "l sit ring pull ups":
                        case "l sit pull ups":
                        case "ring pull ups":
                        case "regular pull ups":
                        case "baby ring pull ups":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Rolls":
                    switch (variation) {
                        case "weighted front lever rolls":
                        case "front lever rolls":
                        case "half front lever rolls":
                        case "baby front lever rolls":
                        case "weighted one handed ring rolls":
                        case "one handed ring rolls":
                        case "archer ring rolls":
                        case "ring rolls":
                        case "regular rolls":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Push":
                    switch (variation) {
                        case "weighted inverted archer push ups":
                        case "weighted archer push ups":
                        case "inverted archer push ups":
                        case "archer push ups":
                        case "weighted inverted ring push ups":
                        case "weighted ring push ups":
                        case "inverted ring push ups":
                        case "ring push ups":
                        case "weighted push ups":
                        case "regular push ups":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Pike":
                    switch (variation) {
                        case "weighted handstand pike press":
                        case "handstand pike press":
                        case "weighted half handstand pike press":
                        case "half handstand pike press":
                        case "supported handstand pike press":
                        case "weighted elevated pike press":
                        case "elevated pike press":
                        case "regular pike press":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Bicep":
                    switch (variation) {
                        case "weighted one handed bicep curls":
                        case "one handed bicep curls":
                        case "weighted bicep curls":
                        case "ring bicep curls":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Chin":
                    switch (variation) {
                        case "l sit weighted ring chin ups":
                        case "l sit weighted chin ups":
                        case "l sit ring chin ups":
                        case "l sit chin ups":
                        case "ring chin ups":
                        case "regular chin ups":
                        case "baby ring chin ups":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Ring":
                    switch (variation) {
                        case "weighted ring layouts":
                        case "weighted ring layouts lvl 3":
                        case "ring layouts":
                        case "ring layouts lvl 3":
                        case "ring layouts lvl 2":
                        case "ring layouts lvl 1":
                        case "baby ring layouts":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Face":
                    switch (variation) {
                        case "weighted one handed face pulls":
                        case "one handed face pulls":
                        case "weighted face pulls":
                        case "face pulls":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Lever":
                    switch (variation) {
                        case "weighted front lever raises":
                        case "front lever raises":
                        case "semi-tucked lever raises":
                        case "tucked lvl 2 lever raises":
                        case "tucked lever raises":
                        case "supported tucked lever raises":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Iron":
                    switch (variation) {
                        case "weighted iron cross pulls":
                        case "iron cross pulls":
                        case "supported iron cross pulls":
                        case "supported grip iron cross pulls":
                        case "supported supported grip iron cross pulls":
                        case "baby supported grip iron cross pulls":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
                case "Pistol":
                    switch (variation) {
                        case "weighted pistol squads":
                        case "pistol squads":
                        case "half way down pistol squads":
                        case "semi supported half way down pistol squads":
                        case "baby pistol squads":
                            exerciseResult.put(variation, repetitions.get(0));
                            return exerciseResult;
                    }
                    break;
            }
        }
        return exerciseResult;
    }
}