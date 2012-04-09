package com.cynical.android.euchre.network;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.cynical.android.euchre.netty.handler.ClientHandler;
import com.cynical.android.euchre.network.exception.IllegalConnectionStateException;
import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.model.CommandPayloadEncoderModel;
import com.cynical.euchre.netty.commands.server.EuchreCommandModel;
import com.cynical.euchre.netty.decoders.EuchreCommandDecoder;
import com.cynical.euchre.netty.encoders.EuchreCommandEncoder;

public class EuchreNetworkService extends Service {
	
	IBinder binder = new EuchreNetworkBinder();
	
	InetSocketAddress serverAddress = new InetSocketAddress("192.168.1.8", 12345);
	
	ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());	
	ClientBootstrap bootstrap = new ClientBootstrap(factory);	
	Channel channel;
	
	public class EuchreNetworkBinder extends Binder {
		EuchreNetworkService getService() {
			return EuchreNetworkService.this;
		}
		
		public EuchreCommandFuture sendCommand(EuchreCommand command, EuchreCommandModel model) throws IllegalConnectionStateException {
			if(!getService().isConnected()) {
				throw new IllegalConnectionStateException();
			}
			return getService().sendCommand(command, model);
			
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onCreate() {
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			public ChannelPipeline getPipeline() throws Exception {
				
				ChannelPipeline pipeline = Channels.pipeline();
				
				// Decoders
		        pipeline.addLast("CommandDecoder", new EuchreCommandDecoder());

		        // Encoders
		        pipeline.addLast("CommandEncoder", new EuchreCommandEncoder());
		        
		        pipeline.addLast("ClientHandler", new ClientHandler());
		        
				return pipeline;
			}
		});
    	
    	bootstrap.setOption("tcpNoDelay", true);
    	bootstrap.setOption("keepAlive", true);
    	
    	ChannelFuture future = bootstrap.connect(serverAddress);
    	if(future.awaitUninterruptibly(10000L)) {
    		if(future.isSuccess()) {
    			channel = future.getChannel();
    		}
    		else {
    			Log.e("ERROR", "Could not connect to server");
    		}
    	}
    	else {
			Log.e("ERROR", "Could not connect to server");
		}
	}
	
	public boolean isConnected() {
		return channel != null && channel.isConnected();
	}

	@Override
	public void onDestroy() {
		// TODO: Terminate the channel
		super.onDestroy();
	}
	
	private EuchreCommandFuture sendCommand(EuchreCommand command, EuchreCommandModel model) {
		CommandPayloadEncoderModel encModel = new CommandPayloadEncoderModel(command, model);
		return new EuchreCommandFuture(channel.write(encModel), command, model);
	}

}
