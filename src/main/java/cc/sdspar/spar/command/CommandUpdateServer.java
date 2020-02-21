package cc.sdspar.spar.command;

import java.util.List;

import cc.sdspar.spar.network.api.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandUpdateServer extends CommandUpdate {

	@Override
	public String getName() {
		return "spupserv";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		System.out.println("Executing on server...");
		Thread installer = new Thread(new Updater.GrabUpdate(sender));
		installer.run();
		
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
