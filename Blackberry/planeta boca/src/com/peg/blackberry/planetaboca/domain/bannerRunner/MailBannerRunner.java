package com.peg.blackberry.planetaboca.domain.bannerRunner;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;

import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.domain.BannerRunner;

public class MailBannerRunner implements BannerRunner {

	public void run(Banner b) {
		Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,new MessageArguments(						
				MessageArguments.ARG_NEW,b.getLink(),b.getNombre(),
				b.getMsj()));
	}

}
