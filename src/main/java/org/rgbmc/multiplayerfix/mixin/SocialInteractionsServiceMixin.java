package org.rgbmc.multiplayerfix.mixin;

import com.mojang.authlib.yggdrasil.YggdrasilSocialInteractionsService;
import org.rgbmc.multiplayerfix.MultiplayerFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mixin(YggdrasilSocialInteractionsService.class)
public class SocialInteractionsServiceMixin {
    @Shadow(remap = false)
    private boolean serversAllowed;
    @Shadow(remap = false)
    private boolean realmsAllowed;
    @Shadow(remap = false)
    private boolean chatAllowed;

    @Inject(at = @At("HEAD"), method = "serversAllowed", cancellable = true, remap = false)
    public void serversAllowed(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "chatAllowed", cancellable = true, remap = false)
    public void chatAllowed(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "realmsAllowed", cancellable = true, remap = false)
    public void realmsAllowed(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "checkPrivileges", cancellable = true, remap = false)
    public void checkPrivileges(CallbackInfo ci) {
        MultiplayerFix.logger.info("Bypassed privilege checks");
        serversAllowed = true;
        realmsAllowed = true;
        chatAllowed = true;
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "fetchBlockList", cancellable = true, remap = false)
    public void fetchBlockList(CallbackInfoReturnable<Set<UUID>> cir) {
        MultiplayerFix.logger.info("Returned empty blocked uuid set");
        cir.setReturnValue(new HashSet<>());
        cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "isBlockedPlayer", cancellable = true, remap = false)
    public void isBlockedPlayer(UUID playerID, CallbackInfoReturnable<Boolean> cir) {
        MultiplayerFix.logger.info("Bypassed player blocked check");
        cir.setReturnValue(false);
        cir.cancel();
    }
}
