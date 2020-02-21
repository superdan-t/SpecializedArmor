package cc.sdspar.spar.command;

import cc.sdspar.spar.network.api.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandUpdateClient extends CommandUpdate {

	@Override
	public String getName() {
		return "spup";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		System.out.println("Executing on client...");
		System.out.println(sender);
		System.out.println(server); // Server either the IntegratedServer or is null if connected, can be used to give warnings
		Thread installer = new Thread(new Updater.GrabUpdate(sender));
		installer.run();
		
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

}
