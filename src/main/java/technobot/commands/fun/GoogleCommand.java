package technobot.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import technobot.TechnoBot;
import technobot.commands.Category;
import technobot.commands.Command;
import technobot.util.embeds.EmbedUtils;

/**
 * Command that googles something for the user.
 *
 * @author TechnoVision
 */
public class GoogleCommand extends Command {

    public GoogleCommand(TechnoBot bot) {
        super(bot);
        this.name = "google";
        this.description = "Google a question.";
        this.category = Category.FUN;
        this.args.add(new OptionData(OptionType.STRING, "question", "The question to ask google", true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String question = event.getOption("question").getAsString();
        if (question.length() > 250) {
            event.replyEmbeds(EmbedUtils.createError("google doesn't like questions longer than 250 characters!")).queue();
            return;
        }

        StringBuilder query = new StringBuilder("?q=");
        String[] words = question.strip().split(" ");
        for (int i = 0; i < words.length; i++) {
            query.append(words[i]);
            if (i+1 != words.length) query.append("+");
        }
        event.reply("http://lmgtfy.com/"+query).queue();
    }
}
