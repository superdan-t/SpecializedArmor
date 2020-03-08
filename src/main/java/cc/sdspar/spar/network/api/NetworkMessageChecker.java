package cc.sdspar.spar.network.api;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.main.Ref;
import cc.sdspar.spar.util.ModConfig;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class NetworkMessageChecker implements Runnable {
	
	private ArrayList<Integer> sessionDisabled;
	public static List<Message> messages;
	private static boolean errored = false;
	
	public static int lastDisplayed = -1;
	
	@Override
    public void run() {
		try {
			
			if (!ModConfig.CHECK_UPDATES) return;
			
			// This is a very lazy way to both create the file and check for the first launch
			File msgFile = new File("config/sdspar_dismissed_messages.txt");
    		if (!msgFile.exists()) {
    			msgFile.createNewFile();
    			while (!NetworkAPIUtils.notifyUser(new TextComponentString(Ref.CHAT_HEADER).appendSibling(new TextComponentTranslation("api.firstlaunch.client")).getFormattedText())) {
    				Thread.sleep(3000);
    			}
    		}
        	updateMessageList();
        	sessionDisabled = new ArrayList<Integer>();
        	boolean playerUnavailable = false;
            while (true) {
                for (Message m : messages) {
                    if (!sessionDisabled.contains(Integer.valueOf(m.id)) && !m.isDisabled() && (m.activates == null || LocalDateTime.now().isAfter(m.activates)) && (m.expires == null || LocalDateTime.now().isBefore(m.expires))) {
                    	if (NetworkAPIUtils.notifyUser(m.content)) {
                    		if (!m.persist) m.disable();
                    		sessionDisabled.add(Integer.valueOf(m.id));
                    		lastDisplayed = m.id;
                    	} else {
                    		playerUnavailable = true;
                    		break;
                    	}
                    }
                }
                if (playerUnavailable) {
                	Thread.sleep(3000);
                } else {
	                updateMessageList();
	                Thread.sleep(ModConfig.UPDATE_INTERVAL * 1000);
                }
            }
        } catch (InterruptedException e) {
        	
        } catch (ConnectException e) {
        	if (!errored) {
        		errored = NetworkAPIUtils.notifyUser("&0[&bSpecializedArmor&0] &cCouldn't connect to the API server.");
        	}
        } catch (MalformedURLException e) {
        	if (!errored) {
        		errored = NetworkAPIUtils.notifyUser("&0[&bSpecializedArmor&0] &cAPI URL is invalid! This should never happen! Either the authors pushed a very bad update (and the update command won't be able to fix this),"
            		+ " or your mod version has been tampered with.");
        	}
		} catch (IllegalArgumentException e) {
        	if (!errored) {
        		errored = NetworkAPIUtils.notifyUser("&0[&bSpecializedArmor&0] &cInvalid syntax while reading message. This is most definitely the author's fault and not yours. Check console for more details.");
        		Main.logger.error("Invalid sytax while parsing: " + e.getMessage());
        		e.printStackTrace();
        	}
        } catch (IOException e) {
        	if (!errored) {
	        	errored = NetworkAPIUtils.notifyUser("&0[&bSpecializedArmor&0] &cIO Error while reading messages. Check console for more details.");
	        	Main.logger.error("IO Error while reading messages: " + e.getMessage());
	            e.printStackTrace();
	        }
		}
    }

	public static void updateMessageList() throws MalformedURLException, IOException {
		List<INetAssembled> inet = NetworkAPIUtils.readObjects(new URL(Ref.API_URL + "/motd.msg"));
		messages = new ArrayList<Message>();
		for (INetAssembled i : inet) {
			if (i instanceof Message) {
				messages.add((Message) i);
			}
		}
	}
	
	public static Message getMessage(int id) {
		for (Message m : messages) {
			if (m.id == id) {
				return m;
			}
		}
		return null;
	}

    

    
    
}
