package ca.samueltaylor.simple_commands.commands;

import ca.samueltaylor.simple_commands.abstract_commands.BaseCommand;
import ca.samueltaylor.simple_commands.helpers.Chat;
import ca.samueltaylor.simple_commands.helpers.TeleportRequests;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

public class TeleportDeny extends BaseCommand {
    static {
        command = "tpdeny";
    }

    @Override
    public int execute(CommandContext<ServerCommandSource> commandContext) throws CommandSyntaxException {
        PlayerEntity player = commandContext.getSource().getPlayer();
        PlayerEntity requester = TeleportRequests.deny(player);

        Chat.send(player, "Denied " + requester.getName() + "'s teleport request");
        Chat.send(requester, player.getName() + " denied your teleport request");

        return Command.SINGLE_SUCCESS;
    }
}
