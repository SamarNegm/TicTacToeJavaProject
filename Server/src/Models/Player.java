/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author MrMr
 */
public class Player {
    private int id;
    private String username;
    private String password;
    private String status;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
  public Player(String username, String passwd, String status,int score) {
        this.username = username;
        this.password = passwd;
        this.status = status;
        this.score=score;
    }
    public Player(int id, String username, String password, String status, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.score = score;
    }

}
