package cc.sdspar.spar.network.api;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.main.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import java.io.File;

public class NetworkAPIUtils {
	
	public static boolean server = false;

	public static List<INetAssembled> readObjects(URL url) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		ArrayList<INetAssembled> list = new ArrayList<INetAssembled>();
		while (reader.ready()) {
            String block = "";
            char inChar;
            do {
                inChar = (char)reader.read();
                if (inChar != '\n') block += inChar;
            } while (inChar != '>' && reader.ready());
            if (inChar == '>') {
            	list.add(getAssembledObject(block));
            }
            block = "";
        }

        reader.close();
		return list;
	}
	
	public static INetAssembled getAssembledObject(String tag) {
		Dictionary<String,String> dict = parseTag(tag);
		String type = dict.get("type");
		INetAssembled obj = null;
		if (type.equals("msg")) {
			obj = new Message();
		} else if (type.equals("distro")) {
			obj = new Version();
		} else if (type.equals("proxy")) {
			obj = new DLProxy();
		}
		if (obj != null) obj.setProperties(dict);
		return obj;
	}
	
	/**
     * Turn an "tag" into a mapping of its properties.
     * @return Dictionary restricted to strings of props
     */
    public static Dictionary<String,String> parseTag(String tag) {
        if (tag.charAt(0) != '<' || tag.charAt(tag.length() - 1) != '>') {
            throw new IllegalArgumentException("Tag not annotated correctly");
        }
        Dictionary<String,String> props = new Hashtable<String,String>();

        //Extract the type from the beginning
        String type;
        if (tag.indexOf(' ') > 0) {
            type = tag.substring(1, tag.indexOf(' '));
        } else {
            type = tag.substring(1, tag.length() - 1);
        }
        if (!type.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Tag is untyped or typed incorrectly.");
        }
        props.put("type", type);

        if (tag.indexOf(' ') > 0) {
            //There should be name=value pairs following the
            tag = tag.substring(tag.indexOf(' ') + 1, tag.length() - 1);
            String seg = "";
            String name = "";
            String value = "";
            boolean quotesOpen = false;
            boolean inVal = false;
            boolean complete = false;
            for (int i = 0; i < tag.length(); i++) {
                char c = tag.charAt(i);
                if (c == '"') {
                    quotesOpen = !quotesOpen;
                    if (inVal && !quotesOpen) {
                        complete = true;
                    }
                } else if (c == '\\') {
                    seg += tag.charAt(++i);
                } else if (quotesOpen) {
                    seg += c;
                } else if (c == ' ') {
                    if (inVal) {
                        complete = true;
                    } else if (!seg.isEmpty()){
                        throw new IllegalArgumentException("Key missing value");
                    }
                }  else if (c == '=') {
                    if (inVal) throw new IllegalArgumentException("Unexpected equals token");
                    else {
                        while(tag.charAt(i + 1) == ' ') i++; //Avoids extra whitespace
                        name = seg;
                        seg = "";
                        inVal = true;
                    }
                } else {
                    seg += c;
                }

                if (i + 1 == tag.length()) {
                    if (inVal && !quotesOpen) complete = true;
                    else if (!inVal) throw new IllegalArgumentException("Key missing value");
                    else throw new IllegalArgumentException("Unterminated quotes");
                }

                if (complete) {
                    value = seg;
                    name = name.toLowerCase();
                    if (name.equals("type")) throw new IllegalArgumentException("Cannot override type");
                    props.put(name, value);
                    name = "";
                    value = "";
                    seg = "";
                    complete = false;
                    inVal = false;
                }

            }
        }

        return props;

    }

	public static boolean notifyUser(String message) {
		if (server) {
			Main.logger.info(message.replace('&', (char) 167));
			return true;
		} else {
			if (Minecraft.getMinecraft().player != null) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message.replace('&', (char) 167)));
				return true;
			}
		}
		return false;
	}
	
	public static class FileDownloader implements Runnable {
		
		private String remote;
		private Path destination;
		private ICommandSender sender;
		private ITextComponent failure;
		private ITextComponent success;

		public FileDownloader(String remote, Path destination, ICommandSender sender, ITextComponent failure, ITextComponent success) {
			this.remote = remote;
			this.destination = destination;
			this.sender = sender;
			this.failure = failure;
			this.success = success;
		}
		
		@Override
		public void run() {
			try {
				List<INetAssembled> proxies;
				proxies = readObjects(new URL(Ref.API_URL + "/proxies.map"));
				URL url = null;
				for (INetAssembled i : proxies) {
					if (i instanceof DLProxy) {
						if (((DLProxy) i).name.equals(remote)) {
							url = ((DLProxy) i).url;
						}
					}
				}
				File df = destination.toFile();
				if (!(df.getParentFile().exists() || df.getParentFile().mkdirs())) {
					throw new Exception("Could not ensure destination");
				}
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(df);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				rbc.close();
				if (df.exists() && df.isFile()) {
					sender.sendMessage(success);
					return;
				}
			} catch (Exception e) {
				Main.logger.error(e.getMessage());
				e.printStackTrace();
			}
			sender.sendMessage(failure);
		}
		
		
		
	}


}
