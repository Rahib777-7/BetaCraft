package pl.betacraft.auth.jsons.microsoft;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import pl.betacraft.auth.Request;
import pl.betacraft.auth.RequestUtil;

public class XBLAuthRequest extends Request {

	public XBLProperties Properties;
	public String RelyingParty = "http://auth.xboxlive.com";
	public String TokenType = "JWT";

	public XBLAuthRequest(String ms_accessToken) {
		REQUEST_URL = "https://user.auth.xboxlive.com/user/authenticate";
		PROPERTIES.put("Content-Type", "application/json");
		PROPERTIES.put("Accept", "application/json");
		Properties = new XBLProperties(ms_accessToken);
	}

	@Override
	public XBLXSTSAuthResponse perform() {
		Gson gson = new Gson();
		String response = RequestUtil.performPOSTRequest(this);

		XBLXSTSAuthResponse ret;
		try {
			ret = gson.fromJson(response, XBLXSTSAuthResponse.class);
		} catch (JsonParseException ex) {
			return null;
		}
		return ret;
	}
}
