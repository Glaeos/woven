package woven.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.Sys;
import woven.Woven;

import java.util.stream.Collectors;

public class ChatBuilder {

    private static final ITextComponent RESET = new TextComponentString("");

    public static final ChatBuilder IGNORE = new ChatBuilder("") {

        @Override
        public ChatBuilder append(String message) {
            return this;
        }

        @Override
        public ITextComponent build() {
            return new TextComponentString("");
        }

        @Override
        public boolean broadcast(World world) {
            return false;
        }

    };

    static {
        RESET.getStyle().setColor(TextFormatting.RESET);
    }

    private ChatBuilder parent;
    private boolean reset;

    private String message;
    private TextFormatting color;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean strikethrough;
    private boolean obfuscated;

    public ChatBuilder(String message) {
        this.parent = null;
        this.reset = true;

        this.message = message;
        this.color = TextFormatting.RESET;
        this.bold = false;
        this.italic = false;
        this.underlined = false;
        this.strikethrough = false;
        this.obfuscated = false;
    }

    public ChatBuilder reset(boolean reset) {
        this.reset = reset;
        return this;
    }

    public ChatBuilder reset() {
        return reset(true);
    }

    public ChatBuilder noReset() {
        return reset(false);
    }

    public ChatBuilder color(TextFormatting color) {
        this.color = color;
        return this;
    }

    public ChatBuilder bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public ChatBuilder bold() {
        return bold(true);
    }

    public ChatBuilder noBold() {
        return bold(false);
    }

    public ChatBuilder italic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public ChatBuilder italic() {
        return italic(true);
    }

    public ChatBuilder noItalic() {
        return italic(false);
    }

    public ChatBuilder underlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public ChatBuilder underlined() {
        return underlined(true);
    }

    public ChatBuilder noUnderline() {
        return underlined(false);
    }

    public ChatBuilder strikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public ChatBuilder strikethrough() {
        return strikethrough(true);
    }

    public ChatBuilder noStrikethrough() {
        return strikethrough(false);
    }

    public ChatBuilder obfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    public ChatBuilder obfuscated() {
        return obfuscated(true);
    }

    public ChatBuilder noObfuscate() {
        return obfuscated(false);
    }

    public ChatBuilder append(String message) {
        ChatBuilder child = new ChatBuilder(message);
        child.parent = this;
        return child;
    }

    private ITextComponent build0() {
        ITextComponent component = new TextComponentString(message);
        Style style = component.getStyle();
        style.setColor(color);
        style.setBold(bold);
        style.setItalic(italic);
        style.setUnderlined(underlined);
        style.setStrikethrough(strikethrough);
        style.setObfuscated(obfuscated);
        return component;
    }

    public ITextComponent build() {
        if (parent == null) {
            return build0();
        }

        ITextComponent component = parent.build();
        if (reset) {
            ITextComponent resetComponent = RESET.createCopy();
            component.appendSibling(resetComponent);
        }

        ITextComponent child = build0();
        component.appendSibling(child);
        return component;
    }

    public boolean broadcast(World world, boolean system) {
        return Woven.broadcast(world, build(), system);
    }

    public boolean broadcast(World world) {
        return broadcast(world, false);
    }

    public boolean send(ICommandSender sender) {
        sender.sendMessage(build());
        return true;
    }

}
