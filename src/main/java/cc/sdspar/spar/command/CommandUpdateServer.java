package cc.sdspar.spar.command;

import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandUpdateServer extends CommandUpdate {

	@Override
	public String getName() {
		return "spupserv";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (server.isDedicatedServer()) {
			super.execute(server, sender, args);
		} else {
			sender.sendMessage(new TextComponentTranslation("command.update.noserver"));
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
