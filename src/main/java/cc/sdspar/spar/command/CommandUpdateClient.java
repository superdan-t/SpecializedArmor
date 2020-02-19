package cc.sdspar.spar.command;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandUpdateClient extends CommandBase {

	@Override
	public String getName() {
		return "spup";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "spup <latest:version> [version]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		sender.sendMessage(new TextComponentString("Unimplemented"));
		
	}
	
	@Override
	public List<String> getAliases() {
		return Lists.newArrayList("update", "sparupdate", "spup", "sparup");
	}



}
