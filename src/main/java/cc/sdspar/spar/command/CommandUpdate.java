package cc.sdspar.spar.command;

import java.nio.file.Paths;
import java.util.List;

import com.google.common.collect.Lists;

import cc.sdspar.spar.network.api.Updater;
import cc.sdspar.spar.network.api.NetworkAPIUtils.FileDownloader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class CommandUpdate extends CommandBase {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (System.getProperty("java.home") == null) {
			throw new CommandException(new TextComponentTranslation("command.update.java").getFormattedText());
		}
		
		if (args.length == 1 && args[0].equalsIgnoreCase("cancel")) {
			Updater.cancel();
			sender.sendMessage(new TextComponentTranslation("command.update.canceled"));
		} else if (args.length == 1 && args[0].equalsIgnoreCase("latest")) {
			if (!Updater.install(sender, null)) {
				sender.sendMessage(new TextComponentString((char) 167 + "c").appendSibling(new TextComponentTranslation("command.update.already_running")));
			}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("version")) {
			if (!Updater.install(sender, args[1])) {
				sender.sendMessage(new TextComponentString((char) 167 + "c").appendSibling(new TextComponentTranslation("command.update.already_running")));
			}
		} else if (args.length == 1 && args[0].equalsIgnoreCase("ModUpgrade")) {
			FileDownloader dl = new FileDownloader("modupgrade-latest", Paths.get(System.getProperty("user.dir")).resolve("mods/ModUpgrade/modupgrade.jar"), sender, new TextComponentTranslation("command.update.modupgrade.failed"), new TextComponentTranslation("command.update.modupgrade.success"));
			Thread dlThread = new Thread(dl);
			dlThread.start();
		} else {
			throw new CommandException(this.getUsage(sender));
		}
		
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/" + this.getName() + " <latest:version:cancel> [version]";
	}
	
	@Override
	public List<String> getAliases() {
		return Lists.newArrayList("update", "sparupdate", "spup", "sparup");
	}
	
}
