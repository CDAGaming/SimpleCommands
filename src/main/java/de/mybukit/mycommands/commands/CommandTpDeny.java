package de.mybukit.mycommands.commands;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import de.mybukit.mycommands.commands.mycomm.TeleportRequests;
import de.mybukit.mycommands.helper.Permission;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CommandTpDeny {
	
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> literal = CommandManager.literal("tpdeny");
		literal.requires((source) -> {
			return Permission.hasperm(source, literal);
        }).executes(context -> execute(context));
            
		dispatcher.register(literal);
		
		LiteralArgumentBuilder<ServerCommandSource> literal1 = CommandManager.literal("tpno");
		literal1.requires((source) -> {
			return Permission.hasperm(source, literal);
        }).executes(context -> execute(context));
            
		dispatcher.register(literal1);
		
/*		dispatcher.register(CommandManager.literal("tpno").redirect(literal.build()));
		dispatcher.register(CommandManager.literal("no").redirect(literal.build()));
		dispatcher.register(CommandManager.literal("n").redirect(literal.build()));
*/
		
            
	}

	public static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();

		if (TeleportRequests.pending(player.getUuid())) {
			player.addChatMessage(new TextComponent("youdenied"), false);
			List<ServerPlayerEntity> playerlist = context.getSource().getMinecraftServer().getPlayerManager().getPlayerList();
			for (int i = 0; i < playerlist.size(); ++ i) {
				if (playerlist.get(i).getUuid().equals(TeleportRequests.fromWho(player.getUuid()))) {
					playerlist.get(i).addChatMessage(new TextComponent("gotdenied"), false);
				}
			}
			TeleportRequests.remove(player.getUuid());

		} else {
			player.addChatMessage(new TextComponent("nonetodeny"), false);
		}
		return 1;
	}
}