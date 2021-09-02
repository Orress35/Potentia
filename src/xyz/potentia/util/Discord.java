package xyz.potentia.util;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import xyz.potentia.Potentia;

public class Discord
{
    public static void startRPC() {
        DiscordRPC drpc = DiscordRPC.INSTANCE;
        String appId = "873908329687171092";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        drpc.Discord_Initialize(appId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "potentia-large";
        presence.details = Potentia.getProjectName();
        presence.state = "NONE";
        drpc.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!presence.details.equals(Potentia.getProjectName()) || !presence.state.equals(Potentia.getFileName())) {
                    presence.details = Potentia.getProjectName();
                    presence.state = Potentia.getFileName();
                    drpc.Discord_UpdatePresence(presence);
                }
                drpc.Discord_RunCallbacks();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                    System.out.println("interrupted");
                }
            }
        }, "RPC-Callback-Handler").start();
    }
}
