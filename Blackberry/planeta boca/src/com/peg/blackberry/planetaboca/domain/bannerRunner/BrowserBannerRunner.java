package com.peg.blackberry.planetaboca.domain.bannerRunner;

import net.rim.blackberry.api.browser.Browser;
import net.rim.blackberry.api.browser.BrowserSession;

import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.domain.BannerRunner;

public class BrowserBannerRunner implements BannerRunner {

	public void run(Banner b) {
		String link = b.getLink();
		if(link != null && link.length() > 0 ){
			BrowserSession bS=Browser.getDefaultSession();
			bS.displayPage(link);
		}
	}

}
