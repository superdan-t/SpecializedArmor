package cc.sdspar.spar.command;

import cc.sdspar.spar.network.api.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandUpdateClient extends CommandUpdate {

	@Override
	public String getName() {
		return "spup";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (System.getProperty("java.home") == null) {
			throw new CommandException(new TextComponentTranslation("command.update.java").getFormattedText());
		}
		
		if (server == null) {
			sender.sendMessage(new TextComponentString((char) 167 + "e").appendSibling(new TextComponentTranslation("command.update.server_warning")));
		}
		if (args.length == 1 && args[0].equalsIgnoreCase("cancel")) {
			Updater.cancel();
			sender.sendMessage(new TextComponentTranslation("command.update.canceled"));
		} else if (args.length == 1 && args[0].equalsIgnoreCase("latest")) {
			if (!Updater.install(sender, null)) {
				sender.sendMessage(new TextComponentString((char) 167 + "c").appendSibling(new TextComponentTranslation("command.update.client.already_running")));
			}
		} else if ( args.length == 2 && args[0].equalsIgnoreCase("version")) {
			if (!Updater.install(sender, args[1])) {
				sender.sendMessage(new TextComponentString((char) 167 + "c").appendSibling(new TextComponentTranslation("command.update.client.already_running")));
			}
		} else {
			throw new CommandException(this.getUsage(sender));
		}
		
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

}
