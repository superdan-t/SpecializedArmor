package cc.sdspar.spar.command;

import java.util.List;

import cc.sdspar.spar.network.api.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandUpdateServer extends CommandUpdate {

	@Override
	public String getName() {
		return "spupserv";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (server instanceof IntegratedServer) {
			sender.sendMessage(new TextComponentTranslation("command.update.noserver"));
		} else {
			if (!Updater.install(sender, null)) {
				sender.sendMessage(new TextComponentString((char) 167 + "c").appendSibling(new TextComponentTranslation("command.update.server.already_running")));
			}
		}
		
	}
	
	@Override
	public List<String> getAliases() {
		List<String> a = super.getAliases();
		a.add("spupserver");
		a.add("spupserv");
		return a;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}



}
