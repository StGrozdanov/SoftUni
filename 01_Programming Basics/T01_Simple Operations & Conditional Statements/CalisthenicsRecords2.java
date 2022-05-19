import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalisthenicsRecords2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, List<Double>> currentExercise;
        Map<String, Double> score = new LinkedHashMap<>();
        List<Double> results = new ArrayList<>();

        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            String exercise = tokens[0];
            String fullExerciseName = findFullName(exercise);
            currentExercise = findExerciseRepetitionsScore(exercise, tokens);
            double result = calculateTotalScore(currentExercise, exercise);
            results.add(result);
            if (result > 0) {
                score.put(fullExerciseName, result);
            } else {
                score.put(fullExerciseName, 0.0);
            }
            input = scanner.nextLine();
        }
        score.forEach((key, value) -> System.out.printf("%s: %.2f points.%n", key, value));
        List<Double> pushWeek = new ArrayList<>(Arrays.asList(14.693333333333332, 7.11, 15.532499999999999, 19.320000000000004, 7.314, 4.939333333333333, 13.6, 27.599999999999998, 12.649999999999999, 4.4, 1.15, 1.1));
        List<Double> pullWeek = new ArrayList<>(Arrays.asList(17.237333333333332, 8.22, 12.98, 17.958000000000002, 6.509, 4.6, 15.299999999999999, 32.199999999999996, 13.799999999999999, 4.4, 9.2, 1.1));

        List<Double> weekTotal = new ArrayList<>(Arrays.asList(10.784097222222222, 0.0, 0.0, 0.0));
        List<Double> records = new ArrayList<>(Arrays.asList(16.826666666666668, 8.22, 15.532499999999999, 19.320000000000004, 7.314, 4.939333333333333, 13.6, 27.599999999999998, 12.649999999999999, 6.370000000000001, 39.099999999999994, 35.2));
        List<Double> monthRecords = new ArrayList<>(Arrays.asList(11.18301851851852, 0.0, 0.0));
        System.out.println(results);

        System.out.println("Please, select week type between push / pull");
        String type = scanner.nextLine();
        switch (type) {
            case "push":
                findTheUpperBodyProgress(pushWeek, score, records);
                break;
            case "pull":
                findTheUpperBodyProgress(pullWeek, score, records);
                break;
            default:
                break;
        }
        findLowerBodyProgress(pullWeek, score, records);
        System.out.println(records);
        System.out.println("For week total: TOTAL WEEK; For month total: TOTAL MONTH");
        String totalType = scanner.nextLine();
        OptionalDouble week;
        OptionalDouble month;
        switch (totalType) {
            case "TOTAL WEEK":
                week = score.values().stream().mapToDouble(Double::doubleValue).average();
                System.out.println(week.getAsDouble());
                break;
            case "TOTAL MONTH":
                week = score.values().stream().mapToDouble(Double::doubleValue).average();
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

    private static void findLowerBodyProgress(List<Double> pullWeek, Map<String, Double> score, List<Double> records) {
        List<Double> newWeek = new ArrayList<>();
        List<String> exercises = new ArrayList<>();
        for (Map.Entry<String, Double> entry : score.entrySet()) {
            double current = entry.getValue();
            newWeek.add(current);
            exercises.add(entry.getKey());
        }
        for (int i = 6; i < newWeek.size(); i++) {
            double nWeek = newWeek.get(i);
            double pWeek = pullWeek.get(i);
            double currentRecord = records.get(i);
            String exercise = exercises.get(i);
            String condition;
            String record;
            if (nWeek > pWeek) {
                condition = "Progress";
            } else if (nWeek < pWeek) {
                condition = "Regress";
            } else {
                condition = "Neutral";
            }
            if (nWeek > currentRecord) {
                record = "*NEW RECORD*";
                records.set(i, nWeek);
            } else if (nWeek < currentRecord) {
                record = "Not a record";
            } else {
                record = "*RECORD*";
            }
            System.out.printf("%s: %s | %s%n", exercise, condition, record);
        }
    }

    private static void findTheUpperBodyProgress(List<Double> pushWeek, Map<String, Double> score, List<Double> records) {
        List<Double> newWeek = new ArrayList<>();
        List<String> exercises = new ArrayList<>();
        for (Map.Entry<String, Double> entry : score.entrySet()) {
            double current = entry.getValue();
            newWeek.add(current);
            exercises.add(entry.getKey());
        }
        for (int i = 0; i < 6; i++) {
            double nWeek = newWeek.get(i);
            double pWeek = pushWeek.get(i);
            double currentRecord = records.get(i);
            String exercise = exercises.get(i);
            String condition;
            String record;
            if (nWeek > pWeek) {
                condition = "Progress";
            } else if (nWeek < pWeek) {
                condition = "Regress";
            } else {
                condition = "Neutral";
            }
            if (nWeek > currentRecord) {
                record = "*NEW RECORD*";
                records.set(i, nWeek);
            } else if (nWeek < currentRecord) {
                record = "Not a record";
            } else {
                record = "*RECORD*";
            }
            System.out.printf("%s: %s | %s%n", exercise, condition, record);
        }
    }


    private static String findFullName(String exercise) {
        switch (exercise) {
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


    private static double calculateTotalScore(Map<String, List<Double>> currentExercise, String exercise) {
        double result = 0;
        if (currentExercise.get(exercise).size() > 1) {
            double max = currentExercise.get(exercise).stream().mapToDouble(Double::doubleValue).max().getAsDouble();
            int n = currentExercise.get(exercise).size();
            for (int i = 0; i < n; i++) {
                if (currentExercise.get(exercise).get(i) < max) {
                    result += currentExercise.get(exercise).get(i);
                }
            }
            result /= (n - 1);
            result /= 10;
            result += max;
            return result;
        } else if (currentExercise.get(exercise).size() == 1) {
            return currentExercise.get(exercise).get(0);
        }
        return 0;
    }

    private static Map<String, List<Double>> findExerciseRepetitionsScore(String exercise, String[] tokens) {
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
                            score = (7 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "bulgarian dips":
                            score = (6 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted ring dips":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "regular ring dips":
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted bar dips":
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "baby ring dips":
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "regular bar dips":
                            score = 2 * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Pull":
                    switch (variation) {
                        case "l sit weighted ring pull ups":
                            score = (7 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "l sit weighted pull ups":
                            score = (6 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "l sit ring pull ups":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "l sit pull ups":
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "ring pull ups":
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "regular pull ups":
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "baby ring pull ups":
                            score = 2 * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Rolls":
                    switch (variation) {
                        case "weighted front lever rolls":
                            score = (9 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "front lever rolls":
                            score = (8 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "half front lever rolls":
                            score = (7 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "baby front lever rolls":
                            score = (6 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted one handed ring rolls":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = 2 * ((repetitions.get(0) / 250) + 1);
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
                        case "weighted inverted archer push ups":
                            score = (10 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted archer push ups":
                            score = (9 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "inverted archer push ups":
                            score = (8 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "archer push ups":
                            score = (7 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted inverted ring push ups":
                            score = (6 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted ring push ups":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "inverted ring push ups":
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "ring push ups":
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted push ups":
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "regular push ups":
                            score = 2 * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                    }
                    break;
                case "Pike":
                    switch (variation) {
                        case "weighted handstand pike press":
                            score = (8 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "handstand pike press":
                            score = (7 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted half handstand pike press":
                            score = (6 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "half handstand pike press":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "supported handstand pike press":
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "weighted elevated pike press":
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = 2 * ((repetitions.get(0) / 250) + 1);
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
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
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
                            score = 2 * ((repetitions.get(0) / 250) + 1);
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
                case "Neck":
                case "Glut":
                case "Reverse":
                case "Squads":
                case "Bulgarian":
                    score = weight.get(0) * ((repetitions.get(0) / 250) + 1);
                    exerciseResult.get(exercise).get(variation).add(score);
                    repetitions.remove(0);
                    weight.remove(0);
                    break;
                case "Pistol":
                    switch (variation) {
                        case "weighted pistol squads":
                            score = (5 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "pistol squads":
                            score = (4 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "half way down pistol squads":
                            score = (3 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "semi supported half way down pistol squads":
                            score = (2 * 2) * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                        case "baby pistol squads":
                            score = 2 * ((repetitions.get(0) / 250) + 1);
                            exerciseResult.get(exercise).get(variation).add(score);
                            repetitions.remove(0);
                            break;
                    }
                    break;
            }
        }
        Map<String, List<Double>> endResult = new LinkedHashMap<>();

        for (Map.Entry<String, Map<String, List<Double>>> outerMap : exerciseResult.entrySet()) {
            for (Map<String, List<Double>> value : exerciseResult.values()) {
                for (Map.Entry<String, List<Double>> entry : value.entrySet()) {
                    if (entry.getValue().size() > 1) {
                        double result = 0;
                        int n = entry.getValue().size();
                        for (int i = 1; i < n; i++) {
                            result += entry.getValue().get(i);
                        }
                        result /= (n - 1);
                        result /= 100;
                        result += entry.getValue().get(0);
                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
                        endResult.get(outerMap.getKey()).add(result);
//                        double average = entry.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
//                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
//                        endResult.get(outerMap.getKey()).add(average);
                    } else {
                        endResult.putIfAbsent(outerMap.getKey(), new ArrayList<>());
                        endResult.get(outerMap.getKey()).add(entry.getValue().get(0));
                    }
                }
            }
        }
        return endResult;
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

    private static List<Double> findRepetitions(String word) {
        List<Double> result = new ArrayList<>();
        String criteria = "[\\d]+[/]?(\\d+)?[/]?(\\d+)[/]?(\\d+)?$";
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {
            result = Arrays.stream(matcher.group().split("/")).map(Double::parseDouble).collect(Collectors.toList());
        }
        return result;
    }
}
