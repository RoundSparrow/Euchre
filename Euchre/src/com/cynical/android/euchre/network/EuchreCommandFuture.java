package com.cynical.android.euchre.network;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import android.util.Log;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.server.EuchreCommandModel;

public class EuchreCommandFuture {
	
	public interface CommandCallback  {
		public void onSuccess();
		public void onFail();
	}
	
	private ChannelFuture future;
	private CommandCallback callback;
	private EuchreCommand command;
	private EuchreCommandModel model;
	
	protected EuchreCommandFuture(ChannelFuture future, EuchreCommand command, EuchreCommandModel model) {
		this.future = future;
		this.command = command;
		this.model = model;
	}
	
	public void addCallback(CommandCallback cb) {
		callback = cb;
		future.addListener(new ChannelFutureListener() {
			
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()) {
					callback.onSuccess();
				}
				else {
					callback.onFail();
					Log.e(command.toString(), model.toLoggingString());
					Log.e("CHANNEL_FUTURE", future.getCause().getMessage());
				}
			}
		});
	}

}
