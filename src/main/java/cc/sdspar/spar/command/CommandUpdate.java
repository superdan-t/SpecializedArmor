package cc.sdspar.spar.command;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class CommandUpdate extends CommandBase {

	@Override
	public String getUsage(ICommandSender sender) {
		return "/" + this.getName() + " <latest:version:cancel> [version]";
	}
	
	@Override
	public List<String> getAliases() {
		return Lists.newArrayList("update", "sparupdate", "spup", "sparup");
	}
	
}
