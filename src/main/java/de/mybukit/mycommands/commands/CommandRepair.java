package de.mybukit.mycommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import de.mybukit.mycommands.helper.MyStyle;
import de.mybukit.mycommands.helper.Permission;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
//import static net.minecraft.server.command.CommandManager.literal;

public class CommandRepair {

	

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> literal = CommandManager.literal("repair");
				literal.requires(source ->  { return Permission.hasperm(source, literal);
        }).executes(context -> execute(context));
          
            dispatcher.register(literal);
        	LiteralArgumentBuilder<ServerCommandSource> literal1 = CommandManager.literal("rp").
    				requires(source ->  { return Permission.hasperm(source, literal);
            }).executes(context -> execute(context));
                
                dispatcher.register(literal1);
	}

	public static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();
		ItemStack item = player.getMainHandStack();
		if(item.isDamaged()) {
		item.setDamage(0);
		player.addChatMessage(new TranslatableComponent("commands.repair.done").setStyle(MyStyle.Green), false);

		//source.sendFeedback(new TranslatableComponent("commands.repair.done", new Object[0]).setStyle(new Style().setColor(TextFormat.YELLOW)), true);
		}else {
		//source.sendFeedback(new TranslatableComponent("commands.repair.failure", new Object[0]).setStyle(new Style().setColor(TextFormat.RED)), true);
			player.addChatMessage(new TranslatableComponent("commands.repair.failure").setStyle(MyStyle.Red), false);

		}
		return Command.SINGLE_SUCCESS;
	}
}
