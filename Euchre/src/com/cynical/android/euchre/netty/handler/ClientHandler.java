package com.cynical.android.euchre.netty.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.cynical.euchre.netty.commands.EuchreCommand;
import com.cynical.euchre.netty.commands.model.CommandPayloadDecodedModel;

public class ClientHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Object obj = e.getMessage();
		if(obj instanceof CommandPayloadDecodedModel) {
			CommandPayloadDecodedModel model = (CommandPayloadDecodedModel) obj;
			EuchreCommand command = model.getCommand();
			switch(command) {
			case LOGIN:
				//Do Login
				break;
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		super.writeRequested(ctx, e);
	}

}
