package ca.samueltaylor.taylor_commands.commands;


import ca.samueltaylor.taylor_commands.helper.HomePoint;
import ca.samueltaylor.taylor_commands.helper.Permission;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

public class CommandSetHome {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> literal = CommandManager.literal("sethome");
		literal.requires((source) -> {
			return Permission.hasperm(source, literal);
		}).executes(context -> execut(context))

		.then(CommandManager.argument("HomeName", StringArgumentType.word()).
				executes(context -> execute(context)));

		dispatcher.register(literal);
	}

	public static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		String args = StringArgumentType.getString(context, "HomeName").toString();
		ServerPlayerEntity player = context.getSource().getPlayer();
		int homes= HomePoint.getHomecounts(player);
		if (homes<5){
			HomePoint home = HomePoint.getHome(player, args);
			if(home ==null){
				HomePoint.setHome(player, args);
				player.sendMessage(new TranslatableText("commands.sethome.done", HomePoint.getHome(player, args).homename), false);

			}else{
				player.sendMessage(new TranslatableText("commands.sethome.failure",args), false);

				player.sendMessage(new TranslatableText("commands.home.list", HomePoint.gethomePoints(player)), false);
			}
		}else{
			player.sendMessage(new TranslatableText("commands.sethome.maximum"), false);
			player.sendMessage(new TranslatableText("commands.home.list", HomePoint.gethomePoints(player)), false);

		}
		return 1;
	}

	public static int execut(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();
		int homes= HomePoint.getHomecounts(player);
		if (homes<5){
			HomePoint home = HomePoint.getHome(player, "home");
			if(home ==null){
				HomePoint.setHome(player, "home");
				player.sendMessage(new TranslatableText("commands.sethome.done", HomePoint.getHome(player, "home").homename), false);

			}else{
				player.sendMessage(new TranslatableText("commands.sethome.failure","home"), false);
				player.sendMessage(new TranslatableText("commands.home.list", HomePoint.gethomePoints(player)), false);

			}
		}else {
			player.sendMessage(new TranslatableText("commands.sethome.maximum"), false);
			player.sendMessage(new TranslatableText("commands.home.list", HomePoint.gethomePoints(player)), false);
		}
		return 1;
	}
}


