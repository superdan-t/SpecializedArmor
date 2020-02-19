package cc.sdspar.spar.network.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

import cc.sdspar.spar.main.Ref;

public class Message implements INetAssembled {

    public String content;
    public String event;
    public int id;
    public boolean persist;
    public LocalDateTime activates;
    public LocalDateTime expires;
    
    public Message() {}
    
    @Override
    public void setProperty(String name, String value) {
    	if (name == "id") id = Integer.valueOf(value);
    	else if (name == "content") content = value;
    	else if (name == "expires") expires = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
    	else if (name == "activates") activates = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
    	else if (name == "persist") persist = value.equals("true");
    	else if (name == "event") event = value;
    }
    
    @Override
    public void setProperties(Dictionary<String,String> props) {
        String value = (String)props.get("content");
        if (value != null) {
            content = value;
        }
        value = (String)props.get("id");
        if (value != null) {
            id = Integer.valueOf(value);
        }
        value = (String)props.get("expires");
        if (value != null) {
            expires = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
        }
        value = (String)props.get("activates");
        if (value != null) {
            activates = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
        }
        value = (String)props.get("persist");
        if (value != null) {
            persist = value.equals("true");
        }
        value = (String)props.get("event");
        if (value != null) {
            event = value;
        }
    }
    
    public static List<Message> getMessages() throws MalformedURLException, IOException {
        ArrayList<Message> list = new ArrayList<Message>();
        NetworkAPIUtils.readObjects(new URL(Ref.API_URL + "/motd.msg"));
        return list;
    }
    
    public static boolean isDisabled(int id) {
        try {
            Scanner scanner = new Scanner(new File("config/sdspar_dismissed_messages.txt"));
            while (scanner.hasNextInt()) {
                if (scanner.nextInt() == id) {
                	scanner.close();
                	return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { }

        return false;
    }

    public boolean isDisabled() {
    	return isDisabled(id);
    }
    
    public static void disable(int id) {
        if (!isDisabled(id)) {
            try {
                FileWriter fw = new FileWriter("config/sdspar_dismissed_messages.txt", true);
                fw.write(String.valueOf(id));
                fw.write('\n');
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disable() {
    	disable(id);
    }

    @Override
    public String toString() {
        return "<msg id=\"" + Integer.toString(id) + "\" activates=\"" +
        activates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm")) + "\" expires=\"" +
        expires.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm")) + "\" content=\"" + content + "\">";
    }

}
