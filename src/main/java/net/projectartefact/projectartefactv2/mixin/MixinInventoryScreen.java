package net.projectartefact.projectartefactv2.mixin;

import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.projectartefact.projectartefactv2.ProjectArtefactV2;
import net.projectartefact.projectartefactv2.utils.AnimationSlideIn;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.font.TextRenderer;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen extends Screen {

    private AnimationSlideIn animationSlideIn;
    private Text artefactHeading;
    private MultilineText artefactsDescription;

    protected MixinInventoryScreen(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        artefactHeading = Text.literal("Artefacts");
        artefactsDescription = MultilineText.create(this.textRenderer, Text.literal("""
            Artefacts grant you extra abilities to
            help boost your game. Earn artefacts by\s
            completing trials."""), 300);
        animationSlideIn = new AnimationSlideIn(-50, this.height / 2 - 35, 25, this.height / 2 - 35, textRenderer.getWidth(artefactHeading), 24, 5);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void render(@NotNull DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // TODO: Extract this to a separate class and call functions from there?
        // TODO: Change size and pos depending on GUI scale?
        // TODO: Change font to Montserrat?
        animationSlideIn.render();
        context.drawText(this.textRenderer, artefactHeading, animationSlideIn.getCurrentX(), animationSlideIn.getCurrentY(), 0xFFFFFF, false);
        // WHY DOES MINECRAFT STILL NOT HAVE A BUILT-IN FONT SIZE SYSTEM MAN
        context.getMatrices().push();
        context.getMatrices().translate((float) animationSlideIn.getCurrentX() / 2, (float) (animationSlideIn.getCurrentY() + 12) / 2, 0);
        context.getMatrices().peek().getPositionMatrix().scale(0.5f, 0.5f, 0.5f);
        artefactsDescription.draw(context, animationSlideIn.getCurrentX(), animationSlideIn.getCurrentY() + 12, 12, 0xC4C6C9);
        context.getMatrices().pop();
    }

}
