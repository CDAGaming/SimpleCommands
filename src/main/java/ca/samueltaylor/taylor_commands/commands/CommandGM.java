package ca.samueltaylor.taylor_commands.commands;

import ca.samueltaylor.taylor_commands.helper.Permission;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.GameMode;

import java.util.Collection;
import java.util.Collections;

public class CommandGM {

	
	
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> literal = CommandManager.literal("gm");
		literal.requires((source) -> {
			return Permission.hasperm(source, literal);
				});

		literal.then((CommandManager.argument("gamemode", IntegerArgumentType.integer(0, 3)).executes((var1x) -> {
			return method_13387(var1x, Collections.singleton(((ServerCommandSource) var1x.getSource()).getPlayer()),
					GameMode.byId(var1x.getArgument("gamemode", Integer.class), GameMode.SURVIVAL));
		})).then(CommandManager.argument("target", EntityArgumentType.players()).executes((var1x) -> {
			return method_13387(var1x, EntityArgumentType.getPlayers(var1x, "target"),
					GameMode.byId(var1x.getArgument("gamemode", Integer.class), GameMode.SURVIVAL));
		})));
	      

		dispatcher.register(literal);


	}
	   private static void method_13390(ServerCommandSource serverCommandSource_1, ServerPlayerEntity serverPlayerEntity_1, GameMode gameMode_1) {
			TranslatableText textComponent_1 = new TranslatableText("gameMode." + gameMode_1.getName(), new Object[0]);
			if (serverCommandSource_1.getEntity() == serverPlayerEntity_1) {
				serverCommandSource_1.sendFeedback(new TranslatableText("commands.gamemode.success.self", new Object[]{textComponent_1}), true);
			} else {
				// @todo not sure how to fix this yet...
//				if (serverCommandSource_1.getWorld().getGameRules().getBoolean("sendCommandFeedback")) {
//					serverPlayerEntity_1.sendMessage(new TranslatableText("gameMode.changed", new Object[]{textComponent_1}));
//				}

				serverCommandSource_1.sendFeedback(new TranslatableText("commands.gamemode.success.other", new Object[]{serverPlayerEntity_1.getDisplayName(), textComponent_1}), true);
			}

		}

		   private static int method_13387(CommandContext<ServerCommandSource> commandContext, Collection<ServerPlayerEntity> collection, GameMode gameMode) {
		      int int_1 = 0;
		   
		      for(ServerPlayerEntity player:collection) {
		    	   if (player.interactionManager.getGameMode() != gameMode) {
		    		   player.setGameMode(gameMode);
			            method_13390((ServerCommandSource)commandContext.getSource(), player, gameMode);
			          
			         }
		      }


		      return int_1;
		   }
}
