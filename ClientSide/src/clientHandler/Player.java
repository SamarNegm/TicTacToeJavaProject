package clientHandler;

import java.util.regex.*;
import org.json.simple.JSONObject;

// Player class
public class Player {

    private int id;
    private String username;
    private String password;
    private String status;
    private int score;
    private static boolean invited = false;
    private String opponent;

    public void Player(int id, String username, String password, String status, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.score = score;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setInvited(boolean invited) {
        Player.invited = invited;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getOpponent() {
        return opponent;
    }

    public boolean getInvited() {
        return invited;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    // User validation with regex
    public boolean checkUsername(String username) {
        boolean check = true;
        if (username.equals("")) {
            check = false;
        } else if (username.length() > 20) {
            check = false;
        } else {
            check = Pattern.matches("^[a-zA-Z1-9-_]*$", username);
        }
        // else {
        // check = Pattern.matches("^[A-Za-z][A-Za-z0-9_]{7,21}$", username);
        // // User should start with an alphabet [A-Za-z], All other characters can be
        // // alphabets.
        // // User contains numbers or an underscore [A-Za-z0-9_].
        // }
        return check;
    }

    // Password validation with regex
    public boolean checkPassword(String password) {
        boolean check = true;
        if (password.equals("")) {
            check = false;
        }
        if (password.length() < 6 || password.length() > 20) {
            check = false;
        }
        // else {
        // check = Pattern.matches("^(?=.*\\d).{4,8}$", password);
        // // Password must be between 4 and 8 digits long and include at least one
        // numeric
        // // digit.
        // }
        return check;
    }

    // Player status handled with the server
    public void updateStatus(String status) {
        JSONObject newstatus = new JSONObject();
        newstatus.put("type", "updateStatus");
        newstatus.put("status", status);
        ClientHandler.sendRequest(newstatus);
        this.status = status;
    }

    // Player score handled with the server
    public void updateScore(String newScore) {
        JSONObject newscore = new JSONObject();
        newscore.put("type", "updateScore");
        newscore.put("score", newScore);
        ClientHandler.sendRequest(newscore);
    }
}
