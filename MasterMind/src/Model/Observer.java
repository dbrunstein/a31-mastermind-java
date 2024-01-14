package Model;

import java.util.ArrayList;

public interface Observer {
    void update(int score, int round, int attempt, Boolean hasEnded);
    void updateHints(ArrayList<String> hints);
}
