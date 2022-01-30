package com.example.pissh;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;
import org.apache.sshd.server.forward.AcceptAllForwardingFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class SshConnection implements Runnable {
    private String username;
    private String host;
    private int port;
    private String password;

    public SshConnection(String username, String host, int port, String password) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Override
    public void run() {
        // Todo: Finish client connection. Read through SshClient.java documentation at top of file.
        SshClient client = SshClient.setUpDefaultClient();
        client.setForwardingFilter(AcceptAllForwardingFilter.INSTANCE);
        client.start();
        // TODO: verify() keeps breaking my code
        try {
            try (ClientSession session = client.connect(this.username, this.host, this.port).verify(3000).getSession()) {
                session.addPasswordIdentity(this.password);
                //session.auth().verify(60000);
                System.out.println("Connection established");

                ClientChannel channel = session.createChannel(Channel.CHANNEL_SHELL);
                System.out.println("Starting shell");

                ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
                channel.setOut(responseStream);

                // Open channel
//                channel.open().verify(5, TimeUnit.SECONDS);
                try (OutputStream pipedIn = channel.getInvertedIn()) {
                    pipedIn.write("pwd\n".getBytes());
                    pipedIn.flush();
                }

                // Close channel
                channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED),
                        TimeUnit.SECONDS.toMillis(5));

                // Output after converting to string type
                String responseString = new String(responseStream.toByteArray());
                System.out.println(responseString);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
