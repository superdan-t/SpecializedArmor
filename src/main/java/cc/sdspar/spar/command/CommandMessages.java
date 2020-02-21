package cc.sdspar.spar.command;

import java.util.List;

import com.google.common.collect.Lists;

import cc.sdspar.spar.network.api.Message;
import cc.sdspar.spar.network.api.NetworkAPIUtils;
import cc.sdspar.spar.network.api.NetworkMessageChecker;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandMessages  extends CommandBase {

	@Override
	public String getName() {
		return "spmsg";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/spmsg <suppress:view> [message id]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (args.length == 0) throw new CommandException(getUsage(sender));
		
		if (args[0].equals("suppress")) {
			if (args.length > 1) {
				try {
					Message.disable(Integer.valueOf(args[1]));
				} catch (NumberFormatException e) {
					throw new CommandException("Message ID must be a number.");
				}
			} else {
				if (NetworkMessageChecker.lastDisplayed != -1) {
					Message.disable(NetworkMessageChecker.lastDisplayed);
					sender.sendMessage(new TextComponentString("That message has been suppressed"));
				} else {
					throw new CommandException("No messages have been displayed recently.");
				}
			}
		} else if (args[0].equals("view")) {
			if (args.length > 1) {
				try {			
					Message requested = NetworkMessageChecker.getMessage(Integer.valueOf(args[1]));
					if (requested != null) {
						NetworkAPIUtils.notifyUser(requested.content);
					} else {
						throw new CommandException("That message does not exist.");
					}
				} catch (NumberFormatException e) {
					throw new CommandException("Message ID must be a number.");
				}
			} else {
				if (NetworkMessageChecker.lastDisplayed != -1) {
					try {
						NetworkAPIUtils.notifyUser(NetworkMessageChecker.getMessage(NetworkMessageChecker.lastDisplayed).content);
					} catch (NullPointerException e) {
						throw new CommandException("That message has been deleted.");
					}
				} else {
					throw new CommandException("No messages have been displayed recently.");
				}
			}
		} else throw new CommandException(getUsage(sender));
		
	}
	
	@Override
	public List<String> getAliases() {
		return Lists.newArrayList("sparmessage", "spmsg", "sparmsg");
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}



}
