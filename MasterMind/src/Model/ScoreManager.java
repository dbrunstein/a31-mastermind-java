package Model;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreManager {

    private static final String SCORE_FILE = "./scores.txt";

    public ScoreManager() {
        File file = new File(SCORE_FILE);
        if (!file.exists()) {
            try {
                System.out.println("Creating score file");
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating score file");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Score file already exists");
        }
    }

    // Méthode pour ajouter un score avec un pseudo
    public static void addScore(String playerName, int score) {

        // Vérifier si le fichier existe, sinon le créer

        try {
            // Lire les scores existants du fichier
            Map<String, Integer> scores = readScores();

            // Vérifier si le joueur a déjà un score enregistré
            if (scores.containsKey(playerName)) {
                // Si le nouveau score est meilleur, le mettre à jour
                if (score > scores.get(playerName)) {
                    scores.put(playerName, score);
                }
            } else {
                // Ajouter le joueur avec son score
                scores.put(playerName, score);
            }

            // Écrire les scores mis à jour dans le fichier
            writeScores(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer le score d'un joueur
    public static int getScore(String playerName) {
        try {
            // Lire les scores existants du fichier
            Map<String, Integer> scores = readScores();

            // Retourner le score du joueur s'il existe, sinon 0
            return scores.getOrDefault(playerName, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Méthode pour lire les scores à partir du fichier
    public static Map<String, Integer> readScores() throws IOException {
        Map<String, Integer> scores = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    scores.put(playerName, score);
                }
            }
        }

        return scores;
    }

    // Méthode pour écrire les scores dans le fichier
    public static void writeScores(Map<String, Integer> scores) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }

    public static List<HashMap<String, Integer>> getTopScores() {
        List<HashMap<String, Integer>> topScores = new ArrayList<>();

        try (Stream<String> lines = Files.lines(new File(SCORE_FILE).toPath())) {
            // Lire les scores du fichier et les transformer en liste de dictionnaires
            topScores = lines.map(line -> {
                String[] parts = line.split(":");
                HashMap<String, Integer> scoreMap = new HashMap<>();
                scoreMap.put(parts[0], Integer.parseInt(parts[1].trim()));
                return scoreMap;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Trier la liste des scores par ordre décroissant
        topScores.sort(Comparator.comparingInt(score -> -score.get(score.keySet().iterator().next())));

        // Limiter la liste aux 5 meilleurs scores
        return topScores.stream().limit(5).collect(Collectors.toList());
    }
}
