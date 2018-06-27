/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Ronald Emilio
 */
public class Client {
    private String name;
    private String points;
    private String ip;

    
    public Client() {
        this.name = "";
        this.points ="";
        this.ip = "";
    }
    public Client(String name, String points, String ip) {
        this.name = name;
        this.points = points;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}

